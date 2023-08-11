package crypto3;

public class Shifr_Pl {
	
		static char[][] tabl = new char [10][10];
		static String alphbt = "abcdefghiklmnopqrstuvwxyz+"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890"
				+ "!№;%:?*()_+/,@#$%^&*[]=-|" +"000000"; //буква i одно и шифруются одинаково с буквой j, поэтому все j будут меняться на i
		static char [] alph = new char[alphbt.length()];

		int k=0;
		static String sKod = Crypto3.kod_s;
		

		
		public static char[] mass(String kod_s) //текст переводим в массив символов
		{
			kod_s = kod_s.toLowerCase();
			char[] tst = kod_s.toCharArray();
			return tst;
		}
		
		public static char[] Zamena (char[] kod){ //замена j на i
			
			  for (int i = 0 ; i < kod.length; i++) {
				  if (kod[i] == 'j') kod[i] = 'i';
				  if (kod[i] =='ё') kod[i] = 'е';
				  if (kod[i] =='ъ') kod[i] = 'x';
				  if (kod[i] =='ь') kod[i] = 'x';

				  
			  	}
		return kod;
		}
		
		
		public static char[] Del_dubl(char[] kod) { //удаляем дубликаты букв		
			 char[] res;
			String rest;
			  StringBuilder sb = new StringBuilder();
			  boolean repeatedChar;
			  for (int i = 0; i < kod.length; i++) {
			      repeatedChar = false;
			      for (int j = i + 1; j <kod.length; j++) {
			          if (kod[i] == kod[j]) {
			              repeatedChar = true;
			              break;
			          }
			      }
			      if (!repeatedChar) {
			          sb.append(kod[i]);
			      }
			  }		  
			  rest = sb.toString();
			 // System.out.println(" " + rest);
			  res = rest.toCharArray();
			  return res;
		}
		
		
		
		
		public static char[][] zap_tabl (char[] kod){ //заполнение таблицы для кодировки
			alph = mass(alphbt);
			kod = mass(Crypto3.kod_s);
			
			alph = mass(alphbt); // массив алфавит
			// код в виде массива
			char [][] bigr = new char[10][10];
			String res;
			int[] kord = new int[kod.length];
			for ( int i = 0; i < kod.length; i++) {
				for ( int j =0; j< alph.length; j++) {
					if (kod[i] == alph[j]) kord[i] = j;
				}
			}			
			int x;
			for ( int k=0; k< kord.length;k++) {
				x = kord[k];
				alph[x] = ' ';
			}	
			res = new String(alph);
			res = res.replaceAll("\\s+","");
			alph = mass(res);			
			int r = -1, st = 0, str = 0;
			for (int i = 0; i < 10; i++) {
				st = 0;
				for (int j = 0; j < 10; j++) {
					r++;
					if ( r < kod.length) {
					bigr[i][j] = kod[r];
					str = i;
					st++;
					}
					else break;
				}
				
			}			
			st++;
			int f = -1;
			for (int i = str; i <10; i++) {
				for (int j = st; j < 10; j++) {	
					f++;
					if ( f< alph.length) {
					bigr[i][j] = alph[f];
					st=0;	
				}
					else break;
				}
			}
			return bigr;
	}  
		
		
		public static String Shiphr(String text) { //char[][] tabl, 
			//System.out.println(alphbt.length());
			char[] kod = mass(sKod);
			kod = Zamena(kod);
			kod = Del_dubl(kod);
			text = text.replaceAll("\\s+",""); //удаляем пробелы в строке кода
			char[] temp = mass(text); //превращаем строку в массив символов
			temp = Zamena(temp);
			temp = Del_dubl(temp);
			tabl = zap_tabl(kod); // таблица для кодирования
			text = new String(temp); // отредактирования строка с текстом который кодируем
			
			char[][] bigrb = null;

			//сначала нужно разбить текст на биграммы (по 2 элемента)
			if (text.length()%2 == 0) { 
				char[] txt = mass(text); //если при делении длины текста на 2 нет остатка - делим текст без дополнения
				bigrb = new char[text.length()/2][2];
				int h = 0;
					for (int i = 0; i<text.length()/2; i++ ) {
						//h++;
						if( h <= txt.length) {
						for (int j = 0; j<2; j++) {
							bigrb[i][j] = txt[h];
							h++;
						}
						}
						else break;
					}
		}			
			if (text.length()%2 != 0) {
				text = new String(text + "x"); //дополняем текст в конце x, чтобы не было остатка от деления
				char[] txt = mass(text);
				
				bigrb = new char[text.length()/2][2];				
				int h = 0;
				for (int i = 0; i <  text.length()/2; i++ ) {
					for (int j = 0; j< 2; j++) {
						bigrb[i][j] = txt[h];
						h++;
					}
				}
		}
			
			char buk1 =' ', buk2 = ' ';
			char[] temp2 = new char [text.length()+1];
			int q = 0, k = 0, r = 0;
			int[][] koordBuk1 = new int [text.length()/2][2];
			int[][] koordBuk2 = new int [text.length()/2][2];
			for ( int i = 0; i< text.length()/2 ; i++) {// цикл по строкам массива, содержащего биграммы	
				buk1 = bigrb[i][0];
				buk2 = bigrb[i][1];	
				for (int p = 0; p < 10; p++) { // цикл по таблице для шифрования
					for (int e = 0; e < 10; e++) { //находим координаты каждой буквы из биграммы в таблице для шифровки
						if (buk1 == tabl[p][e]) {
							koordBuk1[k][0] = p;
							koordBuk1[k][1] = e;
							if (k<=2)k++;
							else break;
							break;
						} else 
						{
							if (buk2 == tabl[p][e]) {
								koordBuk2[r][0] = p;
								koordBuk2[r][1] = e;
								if (r<=2) 
									r++;
								else break;
								break;
						}	
					}	
					}
				}		
			}
			
				for (int strok = 0; strok < koordBuk2.length ; strok++) { //цикл по строкам массива с координатами букв
					
				if ( koordBuk1[strok][0] != koordBuk2[strok][0] && koordBuk1[strok][1] != koordBuk2[strok][1]) {
					temp2[q] = tabl[koordBuk1[strok][0]][koordBuk2[strok][1]];
					q++;
					temp2[q] = tabl[koordBuk2[strok][0]][koordBuk1[strok][1]];
					q++;					
				}
					if (koordBuk1[strok][0] == koordBuk2[strok][0] && koordBuk1[strok][1] != koordBuk2[strok][1]) {
						temp2[q] = tabl[koordBuk1[strok][0]][koordBuk2[strok][1]];
						q++;
						if (koordBuk2[strok][0] >= 9) {
							temp2[q] = tabl[koordBuk2[strok][0]][0];
							q++;
						}
						else {
							temp2[q] = tabl[koordBuk2[strok][0]][koordBuk2[strok][1]+1];
							q++;
						}					
					}
				if (koordBuk1[strok][0] != koordBuk2[strok][0] && koordBuk1[strok][1] == koordBuk2[strok][1]) {
					temp2[q] = tabl[koordBuk1[strok][0]+1][koordBuk1[strok][1]];
					q++;
					if (koordBuk1[strok][0] == 9) {
						temp2[q] = tabl[0][koordBuk2[strok][1]];
						q++;
					}
					if (koordBuk2[strok][0] == 9) 
					{	
						temp2[q] = tabl[0][koordBuk1[strok][1]];
						q++;
					}					
				}
				
			}
			String res = new String (temp2);
			return res;

		
		}	
}

