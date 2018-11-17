package com.example.delano.flashycards;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;
    FlashcardDatabase flashcardDatabase;

    List<Flashcard> allFlashCards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(this);
        allFlashCards = flashcardDatabase.getAllCards();


        if (allFlashCards != null && allFlashCards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashCards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashCards.get(0).getAnswer());
        }


        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashCards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashCards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashCards.get(currentCardDisplayedIndex).getAnswer());
            }
        });


        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the questions to the answer
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            }
        });


        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the questions to the answer
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });



        //Intent for adding new card
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_card.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });



    }
    //Onclick listener
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String question = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("string2");

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);

            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashCards = flashcardDatabase.getAllCards();

        }
    }

}
