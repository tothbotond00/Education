import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FileLogger extends Logger {
  private File file;

  FileLogger (String filename){
      file = new File(filename);
  }

  @Override
  void log (String str){
    try {
      FileWriter pw = new FileWriter(file,true);
      pw.append(str+"\n");
      pw.close();
    }
    catch(IOException e){
      System.err.println("IOException");
    }
  }
}