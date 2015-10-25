package com.fandras.slidingpuzzle;

import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by folde on 10/17/2015.
 */
public class PuzzleBoardLayout extends RelativeLayout
{
    private int size;
    private Context context;
    private PuzzlePieceView blankPuzzlePiece;
    public PuzzleBoardLayout(Context context)
    {
        this(context, 4);
    }
    public PuzzleBoardLayout(Context context, int size)
    {
        super(context);
        this.size = size;
        this.context = context;
    }

    public int[][] generatePuzzle(int size)
    {
        int[][] puzzlePieces = new int[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                //Setting sizes, padding and positions of the PuzzlePieceViews
                PuzzlePieceView puzzlePieceView = new PuzzlePieceView(context, i * size + j);
                int preferredSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, getResources().getDisplayMetrics());
                int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

                puzzlePieces[j][i] = puzzlePieceView.getSequenceIndex();
                puzzlePieceView.setText("" + (puzzlePieceView.getSequenceIndex() + 1));
                puzzlePieceView.setPosition(new Point(j, i));
                puzzlePieceView.setWidth(preferredSize);
                puzzlePieceView.setHeight(preferredSize);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(preferredSize, preferredSize);
                params.leftMargin = j * (padding + preferredSize);
                params.topMargin = i * (padding + preferredSize);
                //Setting the blank PuzzlePieceView to be invisible, and sets it as the layout's blank PuzzlePieceView
                if (i == 3 && j == 3)
                {
                    puzzlePieceView.setVisibility(View.INVISIBLE);
                    blankPuzzlePiece = puzzlePieceView;
                }
                this.addView(puzzlePieceView, params);
            }
        }
        return puzzlePieces;
    }

    public PuzzlePieceView getBlankPuzzlePiece()
    {
        return blankPuzzlePiece;
    }

    //Currently no use, may be used in future
    public PuzzlePieceView getPuzzlePieceFromPosition (Point position)
    {
        for (int i = 0; i < this.getChildCount(); i++)
        {
            PuzzlePieceView currentPiece = (PuzzlePieceView) this.getChildAt(i);
            if (position.equals(currentPiece.getPosition()))
            {
                return currentPiece;
            }
        }
        return null;
    }

}
