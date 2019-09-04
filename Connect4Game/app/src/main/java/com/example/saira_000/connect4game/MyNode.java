package com.example.saira_000.connect4game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MyNode {
    int [][] game = new int[6][7];
    int score =0;
    int col = 0;
    int player = 0 ;
    //ArrayList<MyNode> children = new ArrayList<MyNode>();


    public MyNode(int[][] game , int player ) {
        this.game = game;
        this.player = player;
    }

    public MyNode deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (MyNode) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException el) {
            el.printStackTrace();
            return null;
        }
    }
}
