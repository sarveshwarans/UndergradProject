package Security;
public class Keys {
public Keys() {

}

private int primeNumber = 0;
private int privateKey = 0;
private int publicKey = 0;
private int generator = 0;

public int getPrivateKey(){
return privateKey;
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
public void setGenerator(int generator){
this.generator = generator;
}
public void setPrivatePublicKeys(int privateKey,int primeNumber){
this.privateKey = privateKey;
//this.publicKey = (Math.pow(this.generator,privateKey))%primeNumber;
this.publicKey = getMadValue(generator,primeNumber,privateKey);
}
public void initializeKeys(int generator,int primeNumber){
setPrimeNumber(primeNumber);
setGenerator(generator);
this.privateKey =(int)((primeNumber-2)*(Math.random()));
this.publicKey = getMadValue(generator,primeNumber,privateKey);
//this.publicKey = (Math.pow(this.generator,privateKey))%primeNumber;
}
public EncryptionKey getEncryptionKey(){
EncryptionKey publicKey = new EncryptionKey();
publicKey.setGenerator(this.generator);
publicKey.setPrimeNumber(this.primeNumber);
publicKey.setPublicKey(this.publicKey);
return publicKey;
}

public DecryptionKey getDecryptionKey(){
DecryptionKey privateKey = new DecryptionKey();
privateKey.setPrivateKey(this.privateKey);
privateKey.setPrimeNumber(this.primeNumber);
return privateKey;
}
public int getMadValue(int tempGenerator , int modNumber, int power){
int ReturnModValue = 1;
for(int i =0;i < power; i++){
ReturnModValue =(ReturnModValue*tempGenerator) % (modNumber);
}
return ReturnModValue;
}
}
