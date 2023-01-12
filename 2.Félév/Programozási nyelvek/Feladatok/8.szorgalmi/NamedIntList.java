class NamedIntList extends IntList{

  private String name;

  public NamedIntList(String name, int maxSize){
    super(maxSize);
    this.name = name;
  }

  public NamedIntList(String name, int [] array, int maxSize){
    super(array,maxSize);
    this.name = name;
  }

  @Override
  public String toString(){
    if (array.size() == 0 ){
      return "empty";
    }
    else {
      StringBuilder sb = new StringBuilder();
      sb.append(name + ": ");
      sb.append(array.get(0));
      for (int i = 1; i < array.size(); i++){
        sb.append(" , ");
        sb.append(array.get(i));
      }
      return sb.toString();
    }
  }
}