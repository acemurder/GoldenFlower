package com.acemurder.game;

/**
 * Created by : AceMurder
 * Created on : 2017/11/6
 * Created for : Games.
 * Enjoy it !!!!
 */

/**
 * 牌类，两个属性，花色和点数
 */
public class Poker  implements Comparable<Poker>{

    private Suit suit;
    private Point point;

    public Poker(Suit suit, Point point) {
        this.suit = suit;
        this.point = point;

    }

    public Suit getSuit() {
        return suit;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return suit.desc  + point.toString();
    }

    @Override
    public int compareTo(Poker o) {
        return -(point.getNum() - o.getPoint().getNum());
    }


    public enum Suit {
        SPADE("♠"), CLUB("♣"), HEART("♥"),DIAMOND("♦");

        String desc;

        Suit(String desc) {
            this.desc = desc;
        }
    }



    public enum Point {
        _2(2), _3(3), _4(4), _5(5), _6(6), _7(7), _8(8), _9(9), _10(10), J(11), Q(12), K(13), A(14);


        Point(int num) {
            this.num = num;
        }

        int num;

        public int getNum(){
            return num;
        }
    }
}
