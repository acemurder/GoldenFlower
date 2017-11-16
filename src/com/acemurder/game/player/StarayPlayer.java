//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;
import java.util.Collections;
import java.util.List;

public class StarayPlayer implements Player {
    public StarayPlayer() {
    }

    public String getName() {
        return "周星宇";
    }

    public String getStuNum() {
        return "2017211959";
    }

    public void onGameStart(Manager manager, int totalPlayer) {
    }

    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        int op = this.GETP(pokers);
        if (lastPerson > 5) {
            if (op == 0) {
                return 3 * moneyYouNeedToPayLeast;
            } else if (op == 7) {
                return moneyYouNeedToPayLeast > 4000 ? moneyYouNeedToPayLeast : moneyYouNeedToPayLeast * 2;
            } else if (op == 8) {
                return moneyYouNeedToPayLeast > 2000 ? moneyYouNeedToPayLeast : (int)(1.5D * (double)moneyYouNeedToPayLeast);
            } else {
                return 0;
            }
        } else if (lastPerson > 3) {
            if (op == 0) {
                return 3 * moneyYouNeedToPayLeast;
            } else if (op == 7) {
                return (int)(2.5D * (double)moneyYouNeedToPayLeast);
            } else if (op == 8) {
                return moneyOnDesk > 20000 ? moneyYouNeedToPayLeast : 2 * moneyYouNeedToPayLeast;
            } else if (op == 1) {
                return moneyYouNeedToPayLeast > 1500 ? moneyYouNeedToPayLeast : (int)(1.75D * (double)moneyYouNeedToPayLeast);
            } else if (op == 2) {
                return moneyYouNeedToPayLeast > 1000 ? 0 : (int)(1.5D * (double)moneyYouNeedToPayLeast);
            } else if (op == 3) {
                return moneyYouNeedToPayLeast > 750 ? 0 : (int)(1.25D * (double)moneyYouNeedToPayLeast);
            } else if (op == 4) {
                return moneyYouNeedToPayLeast > 500 ? 0 : moneyYouNeedToPayLeast;
            } else if (op == 5) {
                return moneyYouNeedToPayLeast >= 300 ? 0 : moneyYouNeedToPayLeast;
            } else {
                return 0;
            }
        } else if (op == 0) {
            return 3 * moneyYouNeedToPayLeast;
        } else if (op == 7) {
            return (int)(2.5D * (double)moneyYouNeedToPayLeast);
        } else if (op == 8) {
            return moneyYouNeedToPayLeast > 5000 ? moneyYouNeedToPayLeast : 2 * moneyYouNeedToPayLeast;
        } else if (op == 1) {
            return moneyYouNeedToPayLeast > 2000 ? moneyYouNeedToPayLeast : (int)(1.75D * (double)moneyYouNeedToPayLeast);
        } else if (op == 2) {
            return moneyYouNeedToPayLeast > 1500 ? 0 : (int)(1.5D * (double)moneyYouNeedToPayLeast);
        } else if (op == 3) {
            return moneyYouNeedToPayLeast > 1000 ? 0 : (int)(1.25D * (double)moneyYouNeedToPayLeast);
        } else if (op == 4) {
            return moneyYouNeedToPayLeast > 750 ? 0 : moneyYouNeedToPayLeast;
        } else if (op == 5) {
            return moneyYouNeedToPayLeast > 300 ? 0 : moneyYouNeedToPayLeast;
        } else {
            return 0;
        }
    }

    public void onResult(int time, boolean isWin, List<Poker> pokers) {
    }

    private int GETP(List<Poker> pokers) {
        Collections.sort(pokers);
        if (this.ISC(pokers) && this.ISS(pokers)) {
            return 0;
        } else if (this.ISM(pokers)) {
            return 0;
        } else if (this.ISC(pokers)) {
            return 7;
        } else if (this.ISS(pokers)) {
            return 8;
        } else {
            if (this.ISP(pokers)) {
                if (((Poker)pokers.get(0)).getPoint().getNum() == ((Poker)pokers.get(1)).getPoint().getNum()) {
                    if (((Poker)pokers.get(0)).getPoint().getNum() > 11) {
                        return 1;
                    }

                    return 2;
                }

                if (((Poker)pokers.get(1)).getPoint().getNum() == ((Poker)pokers.get(2)).getPoint().getNum()) {
                    if (((Poker)pokers.get(1)).getPoint().getNum() > 11) {
                        return 1;
                    }

                    return 2;
                }

                if (((Poker)pokers.get(0)).getPoint().getNum() == ((Poker)pokers.get(2)).getPoint().getNum()) {
                    if (((Poker)pokers.get(0)).getPoint().getNum() > 11) {
                        return 1;
                    }

                    return 2;
                }
            }

            int cnt = 0;
            if (((Poker)pokers.get(2)).getPoint().getNum() > 11) {
                ++cnt;
            }

            if (((Poker)pokers.get(1)).getPoint().getNum() > 12) {
                ++cnt;
            }

            if (((Poker)pokers.get(0)).getPoint().getNum() > 13) {
                ++cnt;
            }

            return 6 - cnt;
        }
    }

    private boolean ISC(List<Poker> pokers) {
        return ((Poker)pokers.get(0)).getSuit() == ((Poker)pokers.get(1)).getSuit() && ((Poker)pokers.get(1)).getSuit() == ((Poker)pokers.get(2)).getSuit();
    }

    private boolean ISM(List<Poker> pokers) {
        return ((Poker)pokers.get(0)).getPoint().getNum() == ((Poker)pokers.get(1)).getPoint().getNum() && ((Poker)pokers.get(1)).getPoint().getNum() == ((Poker)pokers.get(2)).getPoint().getNum();
    }

    private boolean ISP(List<Poker> pokers) {
        return ((Poker)pokers.get(0)).getPoint().getNum() == ((Poker)pokers.get(1)).getPoint().getNum() || ((Poker)pokers.get(1)).getPoint().getNum() == ((Poker)pokers.get(2)).getPoint().getNum() || ((Poker)pokers.get(0)).getPoint().getNum() == ((Poker)pokers.get(2)).getPoint().getNum();
    }

    private boolean ISS(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(((Poker)pokers.get(0)).getPoint().getNum() - ((Poker)pokers.get(1)).getPoint().getNum()) == 1 && Math.abs(((Poker)pokers.get(1)).getPoint().getNum() - ((Poker)pokers.get(2)).getPoint().getNum()) == 1;
    }
}
