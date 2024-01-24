package org.example;

import java.math.BigDecimal;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        // Задание 1
        System.out.println(ArithmeticMean(3.14));

        // Задание 2
        var change = Vending(21, 50);
        change.forEach((key, value) -> System.out.println(key + " " + value));

        // Задание 3
        System.out.println(Lenses(new int[] {-1, 0, 1}));
    }

    // <summary> Задание 1 Среднее арифметическое цифр дробной части числа </summary>
    // <param name="x"> Число Х </param>
    // <returns> Среднее арифметическое </returns>
    public static Double ArithmeticMean(Double x) {

        double y = Math.floor(x);                  // Находим целую часть
        int count = BigDecimal.valueOf(x).scale(); // Находим количество цифр дробной части
        int count3 = count;
        int sum = 0;
        int count2 = 10;

        if(x/y == 1) {                             // Проверка на отсутствие дробной части
            return 0.0;
        } else
            while(count != 0){
                sum += Math.floor(x*count2)%10;    // Ищем сумму
                count--;
                count2 *= 10;                      // Шагаем после запятой
            }

        return (double) sum/(double) count3;
    }

    // <summary> Задание 2 ПО для вендингового аппарата </summary>
    // <param name="orderSum"> Сумма заказа </param>
    // <param name="clientSum"> Внесенная сумма клиентом </param>
    // <returns> Сдача в виде словаря { номинал : количество }</returns>
    public static HashMap<Integer, Integer> Vending(int orderSum, int clientSum) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int rez = clientSum - orderSum;
        // Наши номиналы
        int[]coins = {5000, 2000, 1000, 500, 200, 100, 50, 10, 5, 2, 1};
        int count = 0;

        // Проверяем можно ли брать крупными и инкрементируем счетчик
        for (int coin : coins) {
            while (rez - coin >= 0) {
                rez -= coin;
                count++;
            }
            map.put(coin, count);
            count = 0;
        }

        return map;
    }

    // <summary> Задание 3 Заказы на линзы для Инопланетян </summary>
    // <param name="dioptries"> Значения диоптрий каждого Инопланетянина </param>
    // <returns> Количество пар линз </returns>
    public static int Lenses(int[] dioptries) {

        int n = dioptries.length;
        int count = 0;
        int a,b,t;

        for ( a=1; a < n; a++) {
            for (b = n - 1; b >= a; b--) {
                if (dioptries[b - 1] > dioptries[b]) {
                    t = dioptries[b - 1];
                    dioptries[b - 1] = dioptries[b];
                    dioptries[b] = t;
                }
            }
        }

        int i = 0;

        // Цикл для поиска оптимальных вариантов покупки
        while (i < n){
            if(i+1<n && dioptries[i+1]-dioptries[i]<=2){
                count += 1;
                i += 2;
            } else {
                count += 1;
                i += 1;
            }
        }
        return count;
    }
}