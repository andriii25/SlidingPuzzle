package com.fandras.slidingpuzzle;

import android.graphics.Point;

import java.util.HashSet;

/**
 * Created by folde on 10/17/2015.
 */
public class Model
{
    //Storing the sequence index of the puzzle pieces in a matrix
    private int[][] puzzlePieces;

    //Storing the coordinates of the puzzlePieces, may be used in future for checking if a position is valid
    private HashSet<Point> validPositions = new HashSet<>();

    public Model(int[][] puzzlePieces)
    {
        this.puzzlePieces = puzzlePieces;
        for (int i = 0; i < puzzlePieces.length; i++)
        {
            for (int j = 0; j < puzzlePieces[i].length; j++)
            {
                validPositions.add(new Point(i, j));
            }
        }
    }

    public void move(PuzzlePieceView piecePressed, PuzzlePieceView blankPuzzlePiece)
    {
        //Swaps the sequence index and positions of 2 PuzzlePieceViews
        Point piecePressedPosition = piecePressed.getPosition();
        Point blankPuzzlePiecePosition = blankPuzzlePiece.getPosition();
        piecePressed.setPosition(blankPuzzlePiecePosition);
        blankPuzzlePiece.setPosition(piecePressedPosition);
        int temp = puzzlePieces[piecePressedPosition.x][piecePressedPosition.y];
        puzzlePieces[piecePressedPosition.x][piecePressedPosition.y] = blankPuzzlePiece.getSequenceIndex();
        puzzlePieces[blankPuzzlePiecePosition.x][blankPuzzlePiecePosition.y] = temp;
        //1st index is X coordinate, 2nd is Y as we set it in PuzzleBoardLayout's generatePuzzle()

    }

    public int[][] getPuzzlePieces()
    {
        return puzzlePieces;
    }
    public boolean hasWon()
    {
        //Going through the matrix and checking if the current puzzlePiece's sequenceIndex is exactly 1 greater than the previous'
        //If not, hasWon is set to false and the loop breaks
        boolean hasWon = true;

        //Used for comparing the first piece of a row with the last piece of previous row
        int lastSequenceIndex = -1;
        for (int i = 0; i < puzzlePieces.length; i++)
        {
            for (int j = 0; j < puzzlePieces[i].length; j++)
            {
                if(j == 0)
                {
                    //Using [j][i] because we set the 1st index is X coordinate and 2nd is Y
                    //As we want to check the elements of rows then move onto next row instead of columns
                    if (puzzlePieces[j][i] != lastSequenceIndex + 1)
                    {
                        hasWon = false;
                        break;
                    }
                }
                else
                {
                    if (puzzlePieces[j][i] != puzzlePieces[j - 1][i] + 1)
                    {
                        hasWon = false;
                        break;
                    }
                }
                if (j + 1 == puzzlePieces[i].length)
                {
                    lastSequenceIndex = puzzlePieces[j][i];
                }
            }
            if (!hasWon) { break; }
        }
        return hasWon;
    }

}
