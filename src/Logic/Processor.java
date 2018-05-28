package Logic;

public interface Processor {
    public void Process(String Data,CallBack callBack,Sender Sender);
    public void Process(String Data);
}
