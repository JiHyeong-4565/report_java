package section06;

import java.util.Scanner;

public class Javatrpg {

	static boolean battlestart;

	static int monster_experience, monster_money;

	static int hero_Level = 1, hero_power = 15, hero_hp = 80, hero_defense = 25, hero_mp = 0, hero_experience = 0,
			hero_money = 0;

	static int monster_hp, monster_defense, monster_power, monster_mp, monster_level;

	static String hero_name, monster_name;

	static int hero_attack() {
		Scanner in = new Scanner(System.in);
		int sum = 0;

		while (true) {
			System.out.println("1.기본공격");
			System.out.println("2.강타 (공격력의 2배 적용,MP 10 소모)");
			System.out.print("사용할 스킬을 선택하세요:");
			int skill = in.nextInt();
			switch (skill) {
			case 1:
				sum = hero_Level * 10 + hero_power * 30;
				break;
			case 2:

				if (hero_mp >= 10) {
					hero_mp -= 10;
					sum = hero_Level * 10 + hero_power * 60;
				} else {

					System.out.println("마나가 부족하여 기본공격이 실행됩니다.");
					sum = hero_Level * 10 + hero_power * 30;
				}
				break;
			default:
				System.out.println("잘못된 입력입니다 다시 입력해주세요.");
				continue;

			}
			break;

		}
		if (monster_defense >= sum) {
			sum = 0;
			return sum;
		} else {
			return sum;
		}

	}

	static void monster_attacked(int sum) {
		System.out.printf("%s의 공격!\n", hero_name);
		monster_hp = monster_hp + monster_defense - sum;
		if (monster_hp > 0 && sum != 0) {

			System.out.printf("%s는(은) %d데미지를 받았습니다.\n", monster_name, sum);
			System.out.printf("%s의 남은 체력 : %d\n", monster_name, monster_hp);

		} else if (sum == 0) {
			monster_hp -= monster_defense;// 프레젠테이션에서 주어진 계산식으로는 방어력만큼 체력이 회복되어서 다시 방어력을 뺴줌
			System.out.printf("%s는(은) 데미지를 받지 않았습니다.\n", monster_name);
			System.out.printf("%s의 남은 체력 : %d\n", monster_name, monster_hp);
		} else {
			System.out.printf("%s는(은) %d데미지를 받았습니다.\n", monster_name, sum);
			System.out.printf("%s을(를) 처치했습니다.\n", monster_name);
			hero_experience += monster_experience;
			hero_money += monster_money;
			System.out.printf("%s는 경험치 %d와 돈 %d원을 얻었습니다.\n", hero_name, monster_experience, monster_money);
		}

	}

	static int monster_attack() {
		int sum = 0;
		sum = monster_level * 10 + monster_power * 30;
		if (hero_defense >= sum) {
			sum = 0;
			return sum;
		} else {
			return sum;
		}
	}

	static void hero_attacked(int sum) {
		System.out.printf("%s의 공격!\n", monster_name);
		hero_hp = hero_hp + hero_defense - monster_power;
		if (hero_hp > 0 && sum != 0) {

			System.out.printf("%s는(은) %d데미지를 받았습니다.\n", hero_name, monster_power - hero_defense);
			System.out.printf("%s의 남은 체력 : %d\n", hero_name, hero_hp);

		} else if (sum == 0) {
			hero_hp -= hero_defense;
			System.out.printf("%s는(은) 데미지를 받지 않았습니다.\n", hero_name);
			System.out.printf("%s의 남은 체력 : %d\n", hero_name, hero_hp);
		} else {
			System.out.printf("%s는(은) %d데미지를 받았습니다.\n", hero_name, monster_power - hero_defense);
			System.out.printf("%s의 남은 체력 : %d\n", hero_name, hero_hp);
			System.out.printf("%s는(은) 눈앞이 아득해졌습니다.....\n", hero_name);
			System.out.printf("다행히 %s는(은) 빈틈을 노려 도망칠 수 있었습니다.\n", hero_name);
		}

	}

	static void printhero() {
		System.out.println("******************************");
		System.out.println("이름:" + hero_name);
		System.out.printf("%s의 레벨 : %d\n", hero_name, hero_Level);
		System.out.printf("%s의 힘 : %d\n", hero_name, hero_power);
		System.out.printf("%s의 방어력 :%d\n", hero_name, hero_defense);
		System.out.printf("%s의 체력 : %d\n", hero_name, hero_hp);
		System.out.printf("%s의 마나 : %d\n", hero_name, hero_mp);
		System.out.printf("%s의 경험치 : %d\n", hero_name, hero_experience);
		System.out.printf("%s의 소지금 : %d\n", hero_name, hero_money);
		System.out.println("******************************");
	}

	static void printMonster() {
		System.out.println("******************************");
		System.out.println("이름: " + monster_name);
		System.out.println("레벨: " + monster_level);
		System.out.println("힘: " + monster_power);
		System.out.println("방어력: " + monster_defense);
		System.out.println("체력: " + monster_hp);
		System.out.println("마나: " + monster_mp);
		System.out.println("경험치: " + monster_experience);
		System.out.println("소지금: " + monster_money);
		System.out.println("******************************");
	}

	static int potionStore_show(int money, int num) {

		switch (num) {
		case 1:
			if (money >= 30) {
				System.out.println("힘 포션을 구매했습니다.");
				hero_power += 3;
				return money - 30;
			} else {
				System.out.println("소지금이 부족합니다.");
			}
			break;
		case 2:
			if (money >= 30) {
				System.out.println("방어 포션을 구매했습니다.");
				hero_defense += 3;
				return money - 30;
			} else {
				System.out.println("소지금이 부족합니다.");
			}
			break;
		case 3:
			if (money >= 100) {
				System.out.println("경험치 포션을 구매했습니다.");
				hero_experience += 50;
				return money - 100;
			} else {
				System.out.println("소지금이 부족합니다.");
			}
			break;
		case 4:
			if (money >= 10) {
				System.out.println("HP증가 포션을 구매했습니다.");
				hero_hp += 50;
				return money - 10;
			} else {
				System.out.println("소지금이 부족합니다.");
			}
			break;
		case 5:
			if (money >= 10) {
				System.out.println("MP증가포션을 구매했습니다");
				hero_mp += 50;
				return money - 10;
			} else {
				System.out.println("소지금이 부족합니다.");
			}
			break;
		default:
			System.out.println("없는 아이템입니다.");
		}
		return money;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		monster raccoon = new monster();
		raccoon.name = "너구리";
		raccoon.level = 1;
		raccoon.power = 20;
		raccoon.defense = 5;
		raccoon.hp = 100;
		raccoon.mp = 0;
		raccoon.exp = 10;
		raccoon.money = 10;

		monster wildcat = new monster();
		wildcat.name = "솰쾡이";
		wildcat.level = 5;
		wildcat.power = 100;
		wildcat.defense = 20;
		wildcat.hp = 2000;
		wildcat.mp = 0;
		wildcat.exp = 50;
		wildcat.money = 30;

		System.out.print("영웅의 이름을 입력하세요 :");
		hero_name = in.next();
		System.out.println("이름이 입력되었습니다.");
		System.out.println("게임에 입장하였습니다.");

		while (true) {
			while (hero_experience >= hero_Level * 80) {
				hero_Level += 1;
				System.out.printf("%s의 레벨이 %d로 올랐습니다\n", hero_name, hero_Level);
				hero_money += hero_Level * 50;
				hero_hp += 20;
				hero_power += 2;
				hero_defense += 2;

				System.out.printf("레벨 %d로 상승하여 능력치가 올랐습니다 (hp +20 힘 +2 방어력 +2)\n", hero_Level);

				System.out.printf("레벨업 보상으로 %d골드를 획득했습니다.\n", hero_Level * 50);
				hero_experience -= (hero_Level - 1) * 80;

			}
			printhero();

			System.out.println("1.사냥터");
			System.out.println("2.포션 상점");
			System.out.print("입장할 장소를 입력하세요:");
			int locate = in.nextInt();

			switch (locate) {
			case 1:
				System.out.println("사냥터에 입장하였습니다.");
				System.out.println("1.너구리");
				System.out.println("2.솰쾡이");
				System.out.print("전투할 상대를 입력하세요.:");
				int monster_num = in.nextInt();
				switch (monster_num) {
				case 1:
					monster_name = raccoon.name;
					monster_level = raccoon.level;
					monster_power = raccoon.power;
					monster_defense = raccoon.defense;
					monster_hp = raccoon.hp;
					monster_mp = raccoon.mp;
					monster_experience = raccoon.exp;
					monster_money = raccoon.money;
					battlestart = true;
					printMonster();
					System.out.printf("%s와 전투를 시작합니다.\n", monster_name);
					break;
				case 2:
					monster_name = wildcat.name;
					monster_level = wildcat.level;
					monster_power = wildcat.power;
					monster_defense = wildcat.defense;
					monster_hp = wildcat.hp;
					monster_mp = wildcat.mp;
					monster_experience = wildcat.exp;
					monster_money = wildcat.money;
					battlestart = true;
					printMonster();
					System.out.printf("%s와 전투를 시작합니다.\n", monster_name);
					break;
				default:
					System.out.println("올바르지 않은 입력입니다.장소선택으로 되돌아갑니다.");
				}

				break;
			case 2:
				System.out.println("포션상점에 입장하였습니다.");
				System.out.println("1.힘증가 포션(30원)");
				System.out.println("2.방어력 증가 포션(30원)");
				System.out.println("3.경험치증가 포션(100원)");
				System.out.println("4.HP증가 포션(10원)");
				System.out.println("5.MP증가 포션(10원)");
				System.out.printf("현재 소지금 :%d\n", hero_money);
				System.out.print("구매할 물건의 번호를 입력하세요(1~5):");
				int item_num = in.nextInt();
				hero_money = potionStore_show(hero_money, item_num);
				System.out.println("장소 선택으로 되돌아갑니다.");
				break;
			default:
				System.out.println("잘못된 입력입니다.장소를 다시 입력해주세요");

			}

			while (battlestart) {

				monster_attacked(hero_attack());
				if (monster_hp <= 0) {
					battlestart = false;
					break;
				}
				hero_attacked(monster_attack());
				if (hero_hp <= 0) {
					battlestart = false;
					hero_hp = 1;
					break;

				}

			}

		}
	}

	static class monster {
		String name;
		int level;
		int power;
		int defense;
		int hp;
		int mp;
		int exp;
		int money;

	}

}
