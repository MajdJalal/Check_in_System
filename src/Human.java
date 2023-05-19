public abstract class Human implements java.io.Serializable{
    
    protected String name;
    protected int age;

    protected Human (String name) {
        this.name = name;
    }
    protected Human (String name, int age) {
        this.name = name;
        this.age = age;
    }



}
