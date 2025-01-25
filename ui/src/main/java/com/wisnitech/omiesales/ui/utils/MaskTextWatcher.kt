package com.wisnitech.omiesales.ui.utils

import android.text.Editable
import android.text.TextWatcher

object MaskTextWatcher {
    fun unmask(sMask: String, mask: String? = ""): String {
        var s = sMask
        val c = mask?.toCharArray() ?: charArrayOf()

        for (i in c.indices) {
            when (c[i]) {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> if (i < s.length && s[i] == c[i]) {
                    s = s.substring(0, i) + '.'.toString() + s.substring(i + 1)
                }
            }
        }

        return unmaskOnlySymbol(s)
    }

    fun unmaskOnlySymbol(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
            .replace("[)]".toRegex(), "").replace("[+]".toRegex(), "")
            .replace("[,]".toRegex(), "").replace("[$]".toRegex(), "")
            .replace("[ ]".toRegex(), "")
    }

    fun insert(mask: String): TextWatcher {
        return insert(
            arrayOf(mask)
        )
    }

    fun insert(masks: Array<String>) = object : TextWatcher {
        var old = ""

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            old = s.toString()
        }

        override fun afterTextChanged(s: Editable) {
            var mask = masks[masks.size - 1]

            for (other in masks) {
                if (unmask(s.toString(), other).length <= unmask(other, other).length) {
                    mask = other
                    break
                }
            }

            val crude = unmask(s.toString(), mask)

            val str = unmask(s.toString(), mask)

            var mascara = ""

            var i = 0

            for (m in mask.toCharArray()) {
                if (m != '#' && (str.length > old.length || crude.length != i)) {
                    mascara += m
                    continue
                }

                try {
                    mascara += str[i]
                } catch (e: Exception) {
                    break
                }

                i++
            }

            if (s.toString() != mascara) {
                val filters = s.filters
                s.filters = arrayOf()
                s.replace(0, s.length, mascara)
                s.filters = filters
            }
        }
    }

    fun addMask(textToFormat: String, mask: String): String? {
        var formatted: String? = ""
        var i = 0

        for (m in mask.toCharArray()) {
            if (m != '#') {
                formatted += m
                continue
            }

            formatted += try {
                textToFormat[i]
            } catch (e: java.lang.Exception) {
                break
            }

            i++
        }
        return formatted
    }
}