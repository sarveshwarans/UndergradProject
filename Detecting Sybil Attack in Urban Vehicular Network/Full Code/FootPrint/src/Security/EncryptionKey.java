
package Security;

public class EncryptionKey {


private int primeNumber = 0;
private int publicKey = 0;
private int generator = 0;
public EncryptionKey() {
super();
// TODO Auto-generated constructor stub
}

public int getPrimeNumber(){
return primeNumber;
}
public int getPublicKey(){
return publicKey;
}
public int getGenerator(){
return generator;
}

public void setPrimeNumber(int primeNumber){
this.primeNumber = primeNumber;
}

public void setPublicKey(int publicKey){
this.publicKey = publicKey;
}
public void setGenerator(int generator){
this.generator = generator;
}

}