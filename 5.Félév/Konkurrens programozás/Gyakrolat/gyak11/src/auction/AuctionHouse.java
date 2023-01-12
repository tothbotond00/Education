import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class AuctionHouse {
    static class NFT {
        public final int artistIdx;
        public final int price;

        public NFT(int artistIdx, int price) {
            this.artistIdx = artistIdx;
            this.price = price;
        }
    }

    static class AuctionOffer {
        public int offeredSum;
        public String collectorName;

        public AuctionOffer(int offeredSum, String collectorName) {
            this.offeredSum = offeredSum;
            this.collectorName = collectorName;
        }
    }

    static int failCount = 0;

    static final int MAX_NFT_PRICE = 100;
    static final int MAX_NFT_IDX = 100_000;
    static final int MAX_COLLECTOR_OFFER = MAX_NFT_IDX / 100;

    private static final int COLLECTOR_MIN_SLEEP = 10;
    private static final int COLLECTOR_MAX_SLEEP = 20;
    private static final int MAX_AUCTION_OFFERS = 10;

    static final int ARTIST_COUNT = 10;
    static final int COLLECTOR_COUNT = 5;

    static final int INIT_ASSETS = MAX_NFT_IDX / 10 * MAX_NFT_PRICE;

    static int nftIdx = 0;
    static int remainingNftPrice = INIT_ASSETS;
    static NFT[] nfts = new NFT[MAX_NFT_IDX];

    static int totalCommission = 0;
    static int noAuctionAvailableCount = 0;
    static int soldItemCount = 0;

    static final Object nftPriceLock = new Object();
    static BlockingQueue<AuctionOffer> auctionQueue = null;
    static Set<String> owners = new HashSet<>();


    public static void main(String[] args) throws InterruptedException {
        List<Thread> artists = makeArtists();

        Thread auctioneer = makeAuctioneer(artists);

        List<Thread> collectors = makeCollectors(auctioneer);

        for (int i = 0; i < ARTIST_COUNT; i++) {
            artists.get(i).start();
        }
        
        auctioneer.start();
        
        for (int i = 0; i < COLLECTOR_COUNT; i++) {
            collectors.get(i).start();
        }
        
        for(int i = 0; i < ARTIST_COUNT; i++){
            try{ artists.get(i).join();}
            catch(InterruptedException e){ System.out.println("Artist Interrupt"); }
        }
        
        try{ auctioneer.join();}
        catch(InterruptedException e){ System.out.println("Auctioneer Interrupt"); }
        
        for(int i = 0; i < COLLECTOR_COUNT; i++){
            try{ collectors.get(i).join();}
            catch(InterruptedException e){ System.out.println("Collector Interrupt"); }
        }

        runChecks();
    }

    private static List<Thread> makeArtists() {
        List<Thread> artists = new ArrayList<>(ARTIST_COUNT);
        
        for (int i = 1; i <= ARTIST_COUNT; i++) {
            int idx = i;
            artists.add(new Thread(){
                
                @Override
                public void run(){
                    while(true){
                        sleepForMsec(20);
                        int price = getRandomBetween(100, 1000);
                        if (nftIdx == MAX_NFT_IDX) break;
                        if (price > remainingNftPrice) break;
                        NFT nft = new NFT(idx, price);
                        synchronized(nfts){
                            nfts[nftIdx] = nft;
                            nftIdx++;
                        }
                        synchronized(nftPriceLock) {
                            remainingNftPrice -= price; 
                        }
                    }
                }
            });
        }
        return artists;
    }

    private static Thread makeAuctioneer(List<Thread> artists) {
        return new Thread(){

            @Override
            public void run(){
                while(true){
                    int i = 0;
                    for (; i < ARTIST_COUNT; i++) {
                        if(artists.get(i).isAlive()) break;
                    }
                    if (i == ARTIST_COUNT) break;
                    makeAuction();
                }

                for (int i = 0; i < 100; i++) {
                    makeAuction();
                }
            }

            private void makeAuction(){
                if(nftIdx == 0) return;
                NFT randomNFT = nfts[getRandomBetween(0, nftIdx)];
                if(randomNFT == null) return;
                auctionQueue = new ArrayBlockingQueue<>(COLLECTOR_COUNT);
                AuctionOffer bestOffer = null;
                for (int i = 0; i < MAX_AUCTION_OFFERS; i++) {
                    try{
                        AuctionOffer offer = auctionQueue.poll(1, TimeUnit.MILLISECONDS);
                        if (offer == null) break;
                        if(bestOffer == null || bestOffer.offeredSum < offer.offeredSum){
                            bestOffer = offer;
                        }
                    }catch(InterruptedException e){
                        System.out.println("Auctioneer Interrupt");
                    }
                }
                if (bestOffer != null){
                    owners.add(bestOffer.collectorName);
                    soldItemCount++;
                    totalCommission += (int)(randomNFT.price + bestOffer.offeredSum) * 0.1;
                }
                auctionQueue = null;
                sleepForMsec(3);
            }
        };
    }

    private static List<Thread> makeCollectors(Thread auctioneer) {
        List<Thread> collectors = new ArrayList<>();
        for (int i = 1; i <= COLLECTOR_COUNT; i++) {
            int idx = i;
            String name = "Collector" + idx;
            collectors.add(new Thread(){
                
                @Override
                public void run(){
                    BlockingQueue<AuctionOffer> currentAuction = null;
                    while(auctioneer.isAlive()){
                        BlockingQueue<AuctionOffer> auctionQueueCopy = auctionQueue;
                        if (auctionQueueCopy == null) noAuctionAvailableCount++;
                        else {
                            if (auctionQueueCopy != currentAuction){
                                AuctionOffer offer = new AuctionOffer(getRandomBetween(1,MAX_COLLECTOR_OFFER),name);
                                auctionQueueCopy.add(offer);
                                currentAuction = auctionQueueCopy;
                            }
                        }
                        sleepForMsec(getRandomBetween(COLLECTOR_MIN_SLEEP,COLLECTOR_MAX_SLEEP));
                    }
                }
            });
        }
        return collectors;
    }

    // ------------------------------------------------------------------------
    // Tester

    private static String isOK(boolean condition) {
        if (!condition)   ++failCount;
        return isOkTxt(condition);
    }

    private static String isOkTxt(boolean condition) {
        return condition ? "GOOD" : "BAD ";
    }

    private static void runChecks() {
        if (Thread.activeCount() == 1) {
            System.out.printf("%s Only the current thread is running%n", isOK(true));
        } else {
            System.out.printf("%s %d threads are active, there should be only one%n", isOK(Thread.activeCount() == 1), Thread.activeCount());
        }

        System.out.printf("%s nftIdx > 0%n", isOK(nftIdx > 0));

        int soldPrice = IntStream.range(0, nftIdx).map(idx-> nfts[idx].price).sum();
        System.out.printf("%s Money is not lost: %d + %d = %d%n", isOK(soldPrice + remainingNftPrice == INIT_ASSETS), soldPrice, remainingNftPrice, INIT_ASSETS);

        System.out.printf("%s [Only Task 2] Total commission not zero: %d > 0%n", isOK(totalCommission > 0), totalCommission, INIT_ASSETS);

        System.out.printf("%s [Only Task 3] Sold item count not zero: %d > 0%n", isOK(soldItemCount > 0), soldItemCount, INIT_ASSETS);
        System.out.printf("%s [Only Task 3] Some collectors have become owners of NFTs: %d > 0%n", isOK(owners.size() > 0), owners.size(), INIT_ASSETS);
        System.out.printf("%s [Only Task 3] Sometimes, collectors found no auction: %d > 0%n", isOK(noAuctionAvailableCount > 0), noAuctionAvailableCount, INIT_ASSETS);

        System.out.printf("%s Altogether %d condition%s failed%n", isOkTxt(failCount == 0), failCount, failCount == 1 ? "" : "s");

        // forcibly shutting down the program (don't YOU ever do this) - ok Boss xd
        System.exit(42);
    }

    // ------------------------------------------------------------------------
    // Utilities

    private static int getRandomBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    private static void sleepForMsec(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            System.out.println("Sleeping interrupted");
        }
    }
}
