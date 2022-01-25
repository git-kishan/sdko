package com.example.testproject.algorithm

import kotlin.math.sqrt

private fun isSafe(board: Array<IntArray>, row: Int, col: Int, num: Int): Boolean {
    for (d in board.indices) {
        if(d==col)continue
        if (board[row][d] == num) {
            return false
        }
    }
    for (r in board.indices) {
        if(r==row)continue
        if (board[r][col] == num) {
            return false
        }
    }
    val sqrt = sqrt(board.size.toDouble()).toInt()
    val boxRowStart = row - row % sqrt
    val boxColStart = col - col % sqrt
    for (r in boxRowStart until boxRowStart + sqrt) {
        for (d in boxColStart until boxColStart + sqrt) {
            if(r==row && d == col)continue
            if (board[r][d] == num) {
                return false
            }
        }
    }
    return true
}

fun canSolve(board: Array<IntArray>, n: Int): Boolean {
    var row = -1
    var col = -1
    var isEmpty = true
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (board[i][j] == 0) {
                row = i
                col = j
                isEmpty = false
                break
            }
        }
        if (!isEmpty) {
            break
        }
    }
    if (isEmpty) {
        return true
    }
    for (num in 1..n) {
        if (isSafe(board, row, col, num)) {
            board[row][col] = num
            if (canSolve(board, n)) {
                return true
            } else {
                board[row][col] = 0
            }
        }
    }
    return false
}

fun validateSolution(board : Array<IntArray> , n : Int) : Boolean{
    for(row in 0 until n){
        for(col in 0 until n){
            if(!isSafe(board,row,col,board[row][col])){
                return false
            }
        }
    }
    return true
}