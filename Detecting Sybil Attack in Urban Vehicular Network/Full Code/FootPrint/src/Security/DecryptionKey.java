package Security;

public class DecryptionKey {
private int primeNumber = 0;
private int privateKey = 0;
public DecryptionKey()
{
    
}
public int getPrivateKey(){
return privateKey;
}
public int getPrimeNumber(){
return primeNumber;
}
public void setPrivateKey(int privateKey){
this.privateKey = privateKey;
}
public void setPrimeNumber(int primeNumber){
this.primeNumber = primeNumber;
}


}
