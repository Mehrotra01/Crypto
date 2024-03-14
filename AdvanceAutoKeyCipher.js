```
It's not the best code but yes it's what i can build on my own knowledge yet anyone can update and built it a better way 
it surely have some big problems hope you can solve them

only usaable in browser's concole 😂😂😂

```

function encryptAutokey(plaintext, key) {
    let ciphertext = '';
    let extendedKey = key;
    let keyIndex = 0;
    
    for (let i = 0; i < plaintext.length; i++) {
        if (plaintext[i] !== ' ') {
            extendedKey += key[keyIndex % key.length];
            keyIndex++;
        }
    }

    for (let i = 0; i < plaintext.length; i++) {
        if (plaintext[i] === ' ') {
            ciphertext += ' ';
            continue;
        }
        let charCode = (plaintext.charCodeAt(i) + extendedKey.charCodeAt(i)) % 256;
        ciphertext += String.fromCharCode(charCode);
    }
    return ciphertext;
}

function decryptAutokey(ciphertext, key) {
    let plaintext = '';
    let extendedKey = key;
    let keyIndex = 0;
    
    for (let i = 0; i < ciphertext.length; i++) {
        if (ciphertext[i] !== ' ') {
            extendedKey += key[keyIndex % key.length];
            keyIndex++;
        }
    }

    for (let i = 0; i < ciphertext.length; i++) {
        if (ciphertext[i] === ' ') {
            plaintext += ' ';
            continue;
        }
        let charCode = (ciphertext.charCodeAt(i) - extendedKey.charCodeAt(i) + 256) % 256;
        plaintext += String.fromCharCode(charCode);
    }
    return plaintext;
}

function startProgram() {
    const mode = prompt("Enter 1 for encryption or 2 for decryption:");

    if (mode === '1') {
        const plaintext = prompt("Enter the plaintext:");
        const key = prompt("Enter the key:");
        const ciphertext = encryptAutokey(plaintext, key);
        console.log("Ciphertext:", ciphertext);
    } else if (mode === '2') {
        const ciphertext = prompt("Enter the ciphertext:");
        const key = prompt("Enter the key:");
        const plaintext = decryptAutokey(ciphertext, key);
        console.log("Plaintext:", plaintext);
    } else {
        console.log("Invalid mode. Please enter 1 for encryption or 2 for decryption.");
    }
}

startProgram();
