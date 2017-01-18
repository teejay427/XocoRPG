package com.xocogames.xocorpg;

import android.util.Log;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class NameGenerator {

	String newName( Random random ){
		String name = "";
		List<String> vowels = Arrays.asList( "a", "e", "i", "o", "u" );
		List<String> consonants = Arrays.asList( "b", "c", "d", "f", "g", "h", "j", "k", "l", "m",
				"n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z" );

		/* 3 was selected as the minimum length of a generated name,
		and 10 was selected as the maximum length of a generated name */
		int nameLength = random.nextInt( 11 );
		while( nameLength < 3 ){
			nameLength = random.nextInt( 11 );
		}

		/* Determine whether the name has a prefix
		The random number is set high to reduce the chances of a  prefix. This number may need to be
		adjusted in the future to fine tune the number of prefixed names */
		int nextRand;
		nextRand = random.nextInt( 11 );
		switch( nextRand ){
			case 0:
				name += "mc";
				break;
			case 1:
				name += "o'";
				break;
			default:
				break;
		}

		char previousChar = ' ';
		char nextChar;
		while( name.length() < nameLength ){
			/* The highest ASCII number that is a alphabetical character is 122
		    the nextInt function is exclusive with the maximum integer, so a max of 123 strips out
		    the ASCII characters higher than the last alphabetical character */
			nextRand = random.nextInt( 123 );

			// Only use lowercase letters
			if( nextRand < 97 ){
				continue;
			}

			if( name.length() != 0 ){
				previousChar = name.charAt( name.length() - 1 );
			}

			nextChar = ( char ) nextRand;
			switch( nextChar ){
				case 'a':
					// Reduce the chances of having two a's next to each other
					if( previousChar == 'a' ){
						nextRand = random.nextInt( 5 );
						if( nextRand == 0 ){
							name += nextChar;
						}
						else{
							break;
						}
					}


					break;
				case 'q':
					if( name.length() + 2 > nameLength ){
						break;
					}

					if( name.length() + 3 == nameLength ){
						name += "que";
					}
					else if( name.length() + 4 == nameLength ){
						name += "ques";
					}
					else{
						name += "qu";
					}
					break;
				default:
					name += nextChar;
					break;
			}
		}

		// Capitalize the first letter
		String firstLetter = name.substring( 0, 1 );
		name = firstLetter.toUpperCase() + name.substring( 1 );
		return name;
	}

}
