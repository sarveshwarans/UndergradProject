package Security;

import java.util.Arrays;
import java.util.Vector;
import java.util.*;
import javax.swing.*;
public class ElGamal {
public ElGamal() {
super();

}
public String pubkey,prime,generator11,pprime,prikey;
public int ciper;
public String initialize() {
Vector primeVector = new Vector();
Generator generator = new Generator();
int primeNumber = 0;
while (primeVector.isEmpty()) {
primeNumber = generator.createPrimeNumber(1000, 10000);
//System.out.println("--------Prime Number--------");
System.out.println("Prime Number : " + primeNumber);
//Vector pnVector = generator.createPrimeNumberVector(1000, 2357);
primeVector = generator.createGenerator(primeNumber);
}
generator.initializeGenerator(Integer.parseInt(primeVector.elementAt(0)
.toString()), primeNumber);

System.out.println("--------Generator--------");
System.out.println("Generator : " + generator.getGenerator());
System.out.println("Prime Number : " + generator.getPrimeNumber());

// Create Encryption and Decryption keys
Keys keys = new Keys();
keys.initializeKeys(generator.getGenerator(),generator.getPrimeNumber());
EncryptionKey encryptionKey = keys.getEncryptionKey();
DecryptionKey decryptionKey = keys.getDecryptionKey();


System.out.println("--------EncryptionKey--------");
System.out.println("Public Key : " + encryptionKey.getPublicKey());
System.out.println("Generator :" + encryptionKey.getGenerator());
System.out.println("Prime Number : " + encryptionKey.getPrimeNumber());

pubkey=String.valueOf(encryptionKey.getPublicKey());

generator11=String.valueOf(encryptionKey.getGenerator());
prime=String.valueOf(encryptionKey.getPrimeNumber());

System.out.println("--------DecryptionKey--------");
System.out.println("Private Key : " + decryptionKey.getPrivateKey());
System.out.println("Prime Number : " + decryptionKey.getPrimeNumber());
prikey=String.valueOf(decryptionKey.getPrivateKey());
pprime=String.valueOf(decryptionKey.getPrimeNumber());

return pubkey+" "+prikey+" "+generator11+" "+prime;
}

public int[] encrypt(String pubkey,String gene,String prime,String message)
{
// Initialize Encryption
//String encryptionMessage = "suranga kulathunga";
Encryption encryption = new Encryption();
int pub=Integer.parseInt(pubkey);
int generator=Integer.parseInt(gene);
int prim=Integer.parseInt(prime);

encryption.initializeEncryption(pub,generator,prim,message);
encryption.encrypt(message);
System.out.println("--------Encryption--------");
System.out.println("Encryption Message :" + message);
//System.out.println("CipherTextOne : " + encryption.getCipherTextOne());
setciper(encryption.getCipherTextOne());
//System.out.print("CipherTextTwo : ");
for (int i = 0; i < encryption.getCipherTextTwoArray().length; i++) {
//System.out.print(encryption.getCipherTextTwoArray()[i] + " ");
}
System.out.println("");
return encryption.getCipherTextTwoArray();
}
public void setciper(int val)
{
    ciper=val;
}
public int getciper()
{
    return ciper;
}
public String decrypt(String pri,String prime,int x,int y[])
{
// Initialize Decryption
Decryption decryption = new Decryption();
int ppkey1=Integer.parseInt(pri);
int pprime1=Integer.parseInt(prime);
decryption.initializeDecryption(ppkey1,pprime1);
int[] mes = decryption.decrypt(x,
y);
System.out.println("--------Decryption--------");
String message = "";
int ASCII = 0;
System.out.print("Decryption Message :");
for (int i = 0; i < mes.length; i++) {
message += "" + ((char) mes[i]);
System.out.print(mes[i] + " ");
}
System.out.println("");

decryption.setMessage(message);
//System.out.println("Final Message : " + decryption.getMessage());
return decryption.getMessage();

}

}