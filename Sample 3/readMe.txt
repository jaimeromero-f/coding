My code starts by asking the user to enter a bet. This bet is coded such 
that the user can only bet a
number between 1 and 5, and less than the amount in their bankroll (to 
avoid negative bankrolls).

Then, I show the string representation of the cards that are given.

If the cards are inputted from the command line, please input them in 
the following format:
java PokerTest c10 d10 d5 c5 h2 
This is just an example to make sure that my code understands the 
cards and encodes them accordingly. It removes the cards from the 
deck to avoid repeated cards. To do this, I browsed the web for 
possible solutions and use overwritten methods in my Card class.
To avoid repeated draws, I overloaded my Deck constructor,
and allowed a new ArrayList to be inputted as argument that
would then be deleted for the round. At the end of the game,
if the player decides to play again, they would play with a 
full deck.


My code then asks for the number of cards that are going to be changed.
Here, even though I do not ask explicitly for a number between 0 and 5,
my code checks for repeated values. For example, if I say that I will
change 3 cards, then it will prompt me 3 times for an index. If I input
the same number (or a number repeated twice) the code will reprompt the 
user for the number of cards to change and their indices.

In the indices, I use 1,2,3,4,5 and I do not start with 0 to avoid
potential confusion.

Then my code shows the final cards, and the ordered cards by rank. I 
do the last part to make it easier for the user to see their output.

I then ask the user if they want to continue playing! Using 1 to continue
and any other number (preferably 0 to avoid confusion) to exit the game.

When they exit, I print the final bankroll. The game will also exit
if there is not enough money left to play again (if bankroll is 0).

Finally, I update all variables and print the updated bankroll.

I hope you like my game! :)