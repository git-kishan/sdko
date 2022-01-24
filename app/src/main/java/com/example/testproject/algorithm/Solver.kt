package com.example.testproject.algorithm

import kotlin.math.sqrt

class Solver(
    private val mat: Array<IntArray>,
    private val N: Int
) {
    private fun isSafe(
        board: Array<IntArray>,
        row: Int, col: Int,
        num: Int
    ): Boolean {
        for (d in board.indices) {
            if (board[row][d] == num) {
                return false
            }
        }
        for (r in board.indices) {
            if (board[r][col] == num) {
                return false
            }
        }
        val sqrt = sqrt(board.size.toDouble()).toInt()
        val boxRowStart = row - row % sqrt
        val boxColStart = col - col % sqrt
        for (r in boxRowStart until boxRowStart + sqrt) {
            for (d in boxColStart until boxColStart + sqrt) {
                if (board[r][d] == num) {
                    return false
                }
            }
        }
        return true
    }

    fun canSolve(
        board: Array<IntArray>, n: Int
    ): Boolean {
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
}