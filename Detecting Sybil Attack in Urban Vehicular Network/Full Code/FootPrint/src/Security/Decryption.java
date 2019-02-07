package Security;
public class Decryption {

DecryptionKey decryptionKey = null;
private String message ="";
int ppkey;
int pprime;

public Decryption()
{
super();
}

public long FindInverse(long a, long b){
long q =1;
long temp = 0;
long r = 1;
long sign = 1;
long store = a;
long s =0;

while(b!=0){
q=a/b;
temp = r;
r = temp*q+s;
s= temp;
temp = b;
b = a- q*temp;
a = temp;
sign =- sign;

}
long answer = (r-(sign*s))%store;
return answer;
}
public void initializeDecryption(int ppkey,int pprime){
this.ppkey =ppkey;
this.pprime=pprime;
}
public int getModValue(int tempGenerator , int modNumber, int power){
int ReturnModValue = 1;
for(int i =0;i < power; i++){
ReturnModValue =(ReturnModValue*tempGenerator) % (modNumber);
}
return ReturnModValue;
}

public int[] decrypt(int cipherTextOne , int [] cipherTextTwoArray){
int []InverseValue2 = new int[cipherTextTwoArray.length];
long InverseValue = FindInverse(pprime,cipherTextOne);
int InverseValue1 =getModValue((int)InverseValue,pprime,ppkey);
for(int i =0; i < cipherTextTwoArray.length; i++)
{
InverseValue2[i] = (InverseValue1*cipherTextTwoArray[i])%pprime;
}
return InverseValue2;
}
public String getMessage(){
return this.message;
}

public void setMessage(String message){
this.message = message;
}
}
