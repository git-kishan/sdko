package com.example.testproject.algorithm

import kotlin.math.floor
import kotlin.math.sqrt

class Generator internal constructor(
    private val N: Int,
    private val K: Int
) {

    private var mat: Array<IntArray> = Array(N) { IntArray(N) }
    private var SRN: Int = sqrt(N.toDouble()).toInt()
    private val EMPTY_ITEM = 0

    fun generateBoard(): Array<IntArray> {
        fillDiagonal()
        fillRemaining(EMPTY_ITEM, SRN)
        removeKDigits()
        return mat
    }

    private fun fillDiagonal() {
        var i = 0
        while (i < N) {
            fillBox(i, i)
            i += SRN
        }
    }

    private fun unUsedInBox(rowStart: Int, colStart: Int, num: Int): Boolean {
        for (i in 0 until SRN) for (j in 0 until SRN) if (mat[rowStart + i][colStart + j] == num) return false
        return true
    }

    private fun fillBox(row: Int, col: Int) {
        var num: Int
        for (i in 0 until SRN) {
            for (j in 0 until SRN) {
                do {
                    num = randomGenerator(N)
                } while (!unUsedInBox(row, col, num))
                mat[row + i][col + j] = num
            }
        }
    }

    private fun randomGenerator(num: Int): Int {
        return floor(Math.random() * num + 1).toInt()
    }

    private fun checkIfSafe(i: Int, j: Int, num: Int): Boolean {
        return unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % SRN, j - j % SRN, num)
    }

    private fun unUsedInRow(i: Int, num: Int): Boolean {
        for (j in 0 until N) if (mat[i][j] == num) return false
        return true
    }

    private fun unUsedInCol(j: Int, num: Int): Boolean {
        for (i in 0 until N) if (mat[i][j] == num) return false
        return true
    }

    private fun fillRemaining(i: Int, j: Int): Boolean {
        var i = i
        var j = j
        if (j >= N && i < N - 1) {
            i += 1
            j = 0
        }
        if (i >= N && j >= N) return true
        if (i < SRN) {
            if (j < SRN) j = SRN
        } else if (i < N - SRN) {
            if (j == (i / SRN) * SRN) j += SRN
        } else {
            if (j == N - SRN) {
                i += 1
                j = 0
                if (i >= N) return true
            }
        }
        for (num in 1..N) {
            if (checkIfSafe(i, j, num)) {
                mat[i][j] = num
                if (fillRemaining(i, j + 1)) return true
                mat[i][j] = 0
            }
        }
        return false
    }

    private fun removeKDigits() {
        var count = K
        while (count != 0) {
            val cellId = randomGenerator(N * N) - 1
            val i = cellId / N
            var j = cellId % 9
            if (j != 0) j -= 1
            if (mat[i][j] != 0) {
                count--
                mat[i][j] = 0
            }
        }
    }

    fun printSudoku() {
        for (i in 0 until N) {
            for (j in 0 until N) print(mat[i][j].toString() + " ")
            println()
        }
        println()
    }
}