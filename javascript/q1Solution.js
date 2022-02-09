//
// 1) Write two functions:
// 
// - One to retrieve all unique substrings that start and end with a vowel.
// - One to retrieve all unique substrings that start and end with a consonant.
// The resulting array should be sorted in lexicographic ascending order (same order as a dictionary).
// 
// Examples:
// getVowelSubstrings("apple")
// --> ["a", "apple", "e"]
// 
// getVowelSubstrings("hmm") --> []
// 
// getConsonantSubstrings("aviation")
// --> ["n", "t", "tion", "v", "viat", "viation"]
// 
// getConsonantSubstrings("motor")
// -->["m", "mot", "motor", "r", "t", "tor"]
// 
// Notes:
// - Remember the output array should have unique values.
// - The word itself counts as a potential substring.
// - Exclude the empty string when outputting the array. 
//

function isVowelOrConsonant(inputChar, isConsonant) {
    let isAVowel = /[aeiouAEIOU]/.test(inputChar);
    if (isConsonant == true) return !isAVowel;  // It's a consonant
    return isAVowel;
}

function getVowelOrConsonantSubstrings(inputString, isConsonant) {
    const substrSet = new Set();
    let begin = [-1];   // It's an array so we can capture cases of "outer" vowels enclosing "inner" substrings
    let end = -1;
    for (let i=0; i<inputString.length; i++) {
        if ( isVowelOrConsonant(inputString[i], isConsonant) ) {
            
            // edge cases
            if (i==0 && (i+1)<inputString.length && !isVowelOrConsonant(inputString[i+1], isConsonant)) {
                substrSet.add(inputString[i]); // first char is vowel
            }

            if (i==(inputString.length-1) && (i-1)>=0 && !isVowelOrConsonant(inputString[i-1], isConsonant)) {
                substrSet.add(inputString[i]);    // last char is vowel
            }

            // single vowel trapped between two consonants
            if ( i>0 && i<inputString.length && !isVowelOrConsonant(inputString[i-1], isConsonant) && !isVowelOrConsonant(inputString[i+1], isConsonant) ) {
                substrSet.add(inputString[i]);
            }

            if ( begin[begin.length-1]==-1 ) {                 
                begin[begin.length-1] = i;
            }

            if (end <= begin[begin.length-1]) {
                end = i;
            }

            // Get "outer" enclosing substrings            
            for (nextBegin=0; nextBegin<begin.length; nextBegin++) {
                if (end>begin[nextBegin]) {
                    substrSet.add(inputString.substring(begin[nextBegin], end+1))                    
                }
            }
            if (end>begin[begin.length-1]) {
                begin.push(end);    // an ending vowel is also a new beginning so keep track of it
            }
        }
    }

    let substrArray = Array.from(substrSet);
    substrArray.sort( (a,b) => a<b?-1:1 );

    return substrArray;
}

function getVowelSubstrings(inputString) {
    return getVowelOrConsonantSubstrings(inputString, false);
}

function getConsonantSubstrings(inputString) {
    return getVowelOrConsonantSubstrings(inputString, true);
}

// To test, in bash type:
// node q1Solution.js
console.log(getVowelSubstrings("apple"));
console.log(getVowelSubstrings("hmm"));
console.log(getVowelSubstrings("pear"));
console.log(getVowelSubstrings("banana"));
console.log(getVowelSubstrings("humana"));

console.log(getConsonantSubstrings("aviation"));
console.log(getConsonantSubstrings("motor"));
console.log(getConsonantSubstrings("apple"));
console.log(getConsonantSubstrings("hmm"));
console.log(getConsonantSubstrings("pear"));
console.log(getConsonantSubstrings("banana"));
console.log(getConsonantSubstrings("humana"));