package com.fandras.slidingpuzzle;

import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.widget.Button;

/**
 * Created by folde on 10/17/2015.
 */
public class PuzzlePieceView extends Button
{
    //Sequence index is the identifiying number of a PuzzlePiece, the number that is written on the buttons
    //Used for checking the win condition
    private int sequenceIndex;
    private Point position;
    public final int preferredSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, getResources().getDisplayMetrics());
    public final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

    public PuzzlePieceView(Context context, int sequenceIndex)
    {
        this(context, sequenceIndex, new Point(0, 0));
    }

    public PuzzlePieceView(Context context, int sequenceIndex, Point position)
    {
        super(context);
        this.sequenceIndex = sequenceIndex;
        this.position = position;

    }

    public int getSequenceIndex()
    {
        return sequenceIndex;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }


}
