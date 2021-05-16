package com.server.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.BitSet;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhanglishen
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    public static void main(String[] args) {
        containChars("abc");
        System.out.println();
    }
    public static void containChars(String str) {
        BitSet used = new BitSet();
        for (int i = 0; i < str.length(); i++)
            used.set(str.charAt(i)); // set bit for char

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = used.size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            if (used.get(i)) {
                sb.append((char) i);
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
