package com.fandras.slidingpuzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by folde on 10/17/2015.
 */
public class MVCView
{
    private Context context;
    private PuzzleBoardLayout puzzleBoardLayout;
    public MVCView(Context context, PuzzleBoardLayout puzzleBoardLayout)
    {
        this.context = context;
        this.puzzleBoardLayout = puzzleBoardLayout;

    }


    public void drawMessage(String message)
    {
        //Simple message, mostly for debug purposes
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void drawMove(final PuzzlePieceView piecePressed, PuzzlePieceView blankPuzzlePiece)
    {
        //Swapping the positions of 2 PuzzlePieceView
        final RelativeLayout.LayoutParams piecePressedParams = (RelativeLayout.LayoutParams) piecePressed.getLayoutParams();
        final RelativeLayout.LayoutParams blankPuzzlePieceParams = (RelativeLayout.LayoutParams) blankPuzzlePiece.getLayoutParams();

        int tempLeftMargin = piecePressedParams.leftMargin;
        int tempTopMargin = piecePressedParams.topMargin;

        piecePressedParams.leftMargin = blankPuzzlePieceParams.leftMargin;
        piecePressedParams.topMargin = blankPuzzlePieceParams.topMargin;
        piecePressed.setLayoutParams(piecePressedParams);
        blankPuzzlePieceParams.leftMargin = tempLeftMargin;
        blankPuzzlePieceParams.topMargin = tempTopMargin;
        blankPuzzlePiece.setLayoutParams(blankPuzzlePieceParams);

    }

    public AlertDialog.Builder newGameDialogBuilder()
    {
        //Builds a DialogBuilder for the New Game option in the menu/ActionBar
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.new_game)
               .setMessage(R.string.new_game_message);

        return builder;

    }
    public AlertDialog.Builder winDialogBuilder()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.win_game)
               .setMessage(R.string.win_game_message);
        return builder;
    }

    public void showNewGameDialog(AlertDialog.Builder newGameDialogBuilder)
    {
        //After the listeners have been set in the Controller, creates the Dialog here and shows it
        AlertDialog dialog = newGameDialogBuilder.create();
        dialog.show();
    }

    public void showHelpDialog()
    {
        //Creates and shows a dialog for the Help option in the menu/ActionBar
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.help)
               .setMessage(R.string.help_message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void showWinDialog(AlertDialog.Builder builder)
    {
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
