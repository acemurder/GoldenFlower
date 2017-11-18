package com.acemurder.game.player;
import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;
import java.lang.reflect.Field;
import java.lang.Class;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Bridge_Players implements Player  {

/*Class c1=Class.forName("com.acemurder.game.Manager");
Object x=c1.newInstance();*/

    public Bridge_Players() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    }


    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }


    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
       int a=0,b=0,c=0,d=0,f=0;
        Collections.sort(pokers);
        if (isSameColor(pokers) )
        { a++;
            return 3*moneyYouNeedToPayLeast;
        }
        if ( isSameColorStraight(pokers)) {
            b++;
            return 3 * moneyYouNeedToPayLeast;
        }
        if(isSamePoint(pokers))
        {c++;
            return moneyYouNeedToPayLeast * 3;}
        if (isPair(pokers)&&moneyYouNeedToPayLeast<1500&&moneyOnDesk>1.7*moneyYouNeedToPayLeast&&round<2)
        {d++;
            return(int) 1.2*moneyYouNeedToPayLeast;}
        if (isStraight(pokers)&&moneyYouNeedToPayLeast<3000&&moneyOnDesk>2.5*moneyYouNeedToPayLeast&&round<3)
        {f++;
            return 2*moneyYouNeedToPayLeast;
        }
        for (Poker p : pokers){
            if ( p.getPoint().getNum() >= 12&&round < 2)
                return moneyYouNeedToPayLeast;
        }


        if(time>500)
        {
if(isSamePoint(pokers))
{
    return moneyYouNeedToPayLeast * 3;
}
if(isSameColor(pokers))
{
    if(b+c<30)
    {
        return moneyYouNeedToPayLeast*3;
    }
    else
    {
        return (2*moneyYouNeedToPayLeast+(500-(b+c))/500*moneyYouNeedToPayLeast);
    }
}
if(isSameColorStraight(pokers)){
    if(c<20)
    {
        return moneyYouNeedToPayLeast*3;
    }
    else
    {
        return(int)(2*moneyYouNeedToPayLeast+((1-c)/500)*moneyYouNeedToPayLeast);
    }
}
if(isStraight(pokers)&&moneyYouNeedToPayLeast<3000&&moneyOnDesk>2.5*moneyYouNeedToPayLeast&&round<3)
{
    if(a+b+c<200)
    {
        return 2 *moneyYouNeedToPayLeast;
    }
    else
    {
        return (int)1.5*moneyYouNeedToPayLeast+((1-(a+b+c))/500*moneyYouNeedToPayLeast);
    }
}
if(isPair(pokers)&&moneyYouNeedToPayLeast<1500&&moneyOnDesk>1.7*moneyYouNeedToPayLeast)
{
    if(a+b+c+f<300)
    {
        return(int)1.2*moneyYouNeedToPayLeast;
    }
    else
    {
        return moneyYouNeedToPayLeast+((1-(a+b+c+f))/500)*moneyYouNeedToPayLeast;
    }
}
        }
        return 0;
    }


    @Override
     public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    @Override
    public String getName() {
        return "张俊豪";
    }

    @Override
    public String getStuNum() {
        return "2017214133";
    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isPair(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }


    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }

}


