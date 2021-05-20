package com.terheyden.valid;

import java.util.List;

import com.terheyden.valid.annotation.NotEmpty;

/**
 * MyStatic class.
 */
public final class MyStatic {

    private MyStatic() {
        // Private since this class shouldn't be instantiated.
    }

    public static String staticMethod1(@NotEmpty String arg1, @NotEmpty List<String> arg2) {

        Validate.validateArgs(MyStatic.class, arg1, arg2);

        StringBuilder bui = new StringBuilder();
        bui.append(arg1);
        arg2.forEach(bui::append);

        return bui.toString();
    }
}
