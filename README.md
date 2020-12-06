# cryptME 
######  CryptME (V1.0) by toxicspirits
**CryptME** is an open-source encryption application for Android. cryptME allows you to encrypt your data. The application uses **Advanced Encryption Standard (AES)**. It features a simple and easy to use UI that anyone can use to encrypt their confidential data. 


## Mode of operation & Key size 

As of now this application uses AES in CBC (Cipher Block Chaining) mode of operation. More about CBC mode of operation can be read [here.](https://en.wikipedia.org/wiki/Block_cipher_mode_of_operation#Cipher_block_chaining_%28CBC%29) 

CryptME uses 256 bits key. 256 bit key size is considered to be sufficiently strong.

## Installing the application on Android Smartphones
### System Requirements: 
Android Version: 5.1 (Lollipop) or above

There are no specific minimum hardware requirements. The app is very small (less than 4 MB) at this stage. So it can run even on the even on the most potato device. However, this may change as the app size will grow in future and new features will added. 

### Installation:
1. Locate the apk. It's under app/app-debug.apk. Click on app-debug.apk.
2. Download the apk. 
3. Turn on the "Allow installation from unknown sources" setting on your device.
4. Click on the downloaded apk and install it. 

## Usage:

Using the application is pretty straight-forward and basic. After opening the application you'll have the main screen with two options: Encrypt & Decrypt.

Click on Encrypt to encrypt some data. Enter the data you want to encrypt and click encrypt. You can then store the encrypted text, the Secret Key and IV someplace safe. (See Usage warning)

To decrypt the data, enter the encypted data, your secret key and IV and click decrypt. 

## Usage precautions:

While this application is safe by every means, there are still few shortcomings that this app itself cannot overcome. Such as:

1. Key management and storage: The secret key is important. This app doesn't provide any in-build functionality for key storage. Hence, it's the users responsibilty to store the keys in an secured, isolated environment.
2. Data Integrity: While using this app to transfer encrypted data, make sure the transfer is done through a secure channel. There's no in-build feature to verify the data integrity (although this feature may be added soon).
3. Side channel attakcs: The AES algorithm, among other algorithms, is prone to side channel attacks such as cache-timing attack, differential fault analysis etc. Giving any un-authorised person the access to your hardware is simply a very bad bad idea.

With a little bit of precaution, users of this app can be assured that their data remains confidential.
