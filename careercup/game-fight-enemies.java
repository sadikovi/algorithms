package test;

/*
scala> import test.Test._
import test.Test._
scala> val arr = Array(new Enemy(2, 2), new Enemy(1, 1), new Enemy(3, 8), new Enemy(4, 6))
scala> test.Test.play(1, 10, arr)
res0: Int = 7
scala> test.Test.play(1, 4, arr)
res1: Int = 1
*/
public class Test {
  public static class Enemy {
    int strength;
    int price;

    public Enemy(int strength, int price) {
      this.strength = strength;
      this.price = price;
    }
  }

  private static int faceEnemy(int strength, int money, Enemy[] enemies, int i) {
    if (money < 0) return -1;
    if (enemies == null || enemies.length <= i) return money;
    Enemy enemy = enemies[i];
    int res = -1;
    if (strength >= enemy.strength) {
      res = Math.max(res, faceEnemy(strength, money, enemies, i + 1));
    }
    if (money >= enemy.price) {
      res = Math.max(res,
        faceEnemy(strength + enemy.strength, money - enemy.price, enemies, i + 1));
    }
    return res;
  }

  public static int play(int strength, int money, Enemy[] enemies) {
    money = faceEnemy(strength, money, enemies, 0);
    return money < 0 ? -1 : money;
  }
}
