package com.fandras.slidingpuzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by folde on 10/17/2015.
 */
public class Controller
{
    private PuzzleBoardLayout puzzleBoardLayout;
    private int size;
    private Model model;
    private MVCView mvcView;

    private final int shuffleAmount = 2000;

    public Controller(Context context, int size)
    {
        this.puzzleBoardLayout = new PuzzleBoardLayout(context, size);
        this.mvcView = new MVCView(context, puzzleBoardLayout);
        this.model = new Model(puzzleBoardLayout.generatePuzzle(size));
        //Setting Listeners for PuzzlePieceViews
        for (int i = 0; i < puzzleBoardLayout.getChildCount(); i++)
        {
            final PuzzlePieceView puzzlePiece = (PuzzlePieceView) puzzleBoardLayout.getChildAt(i);
            puzzlePiece.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    move(puzzlePiece, puzzleBoardLayout.getBlankPuzzlePiece());
                    if (model.hasWon())
                    {
                        mvcView.drawMessage("Congratulations you won!");
                        Controller.this.winDialog();
                        //TODO: Other things that happen at winning the game, duh
                    }
                }


            });
        }
    }

    public void winDialog()
    {
        AlertDialog.Builder builder = mvcView.winDialogBuilder();
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                resetGame();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        mvcView.showWinDialog(builder);
    }


    public PuzzleBoardLayout getPuzzleBoardLayout()
    {
        return puzzleBoardLayout;
    }

    public void shuffle()
    {
        //Simply making a lot of random moves, and with that it shuffles the board
        for (int i = 0; i < this.shuffleAmount; i++)
        {
            Random random = new Random();
            PuzzlePieceView randomPiece = (PuzzlePieceView) puzzleBoardLayout.getChildAt(random.nextInt(puzzleBoardLayout.getChildCount()));
            //Log.i("Controller/Shuffle", String.format("I: %d R: %s ; B: %s", i, randomPiece.getPosition().toString(), puzzleBoardLayout.getBlankPuzzlePiece().getPosition().toString()));
            this.move(randomPiece, puzzleBoardLayout.getBlankPuzzlePiece());

        }
    }

    private void move(PuzzlePieceView piecePressed, PuzzlePieceView blankPuzzlePiece)
    {
        Point piecePressedPosition = piecePressed.getPosition();
        Point blankPuzzlePiecePosition = blankPuzzlePiece.getPosition();

        //Checking if move is legal
        if (piecePressedPosition.x == blankPuzzlePiecePosition.x && piecePressedPosition.y - 1 == blankPuzzlePiecePosition.y ||
            piecePressedPosition.x == blankPuzzlePiecePosition.x && piecePressedPosition.y + 1 == blankPuzzlePiecePosition.y ||
            piecePressedPosition.x + 1 == blankPuzzlePiecePosition.x && piecePressedPosition.y == blankPuzzlePiecePosition.y ||
            piecePressedPosition.x - 1 == blankPuzzlePiecePosition.x && piecePressedPosition.y == blankPuzzlePiecePosition.y)
        {
            //If yes, then first setting the values in the model, then drawing it
            model.move(piecePressed, blankPuzzlePiece);
            mvcView.drawMove(piecePressed, blankPuzzlePiece);
        }
        else
        {
           //TODO: Feedback to user about wrong move
        }
    }


    public void newGameDialog()
    {
        //Creating a newGameDialog from the MVCView's builder, and setting the listeners here
        AlertDialog.Builder newGameDialogBuilder = mvcView.newGameDialogBuilder();
        newGameDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //On pressing OK it starts a new game
                resetGame();
            }
        });
        newGameDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Otherwise dismisses the dialog
                dialog.dismiss();
            }
        });

        mvcView.showNewGameDialog(newGameDialogBuilder);
    }

    public void resetGame()
    {
        shuffle();
        //TODO: Other possible resets for the future (timer, count of moves)
    }

    public void help()
    {
        mvcView.showHelpDialog();
    }
}
