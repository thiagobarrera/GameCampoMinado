package jogocampominado;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author thiago.barrera
 */
public class JogoCampoMinado {
    public static void main(String[] args) {
        game();
    }
    public static void game() {
        Scanner ler = new Scanner(System.in);
        int Linha, Coluna; 
        int Loop=-1, NumCoord=0, NumBomb=0, AreaBomb=0, Acao=0;
        int Dific=0, CheckPosi=0, Result=0, ContAcao=0, BkpBomb=0;
        boolean Final=false;
        System.out.println("Bem vindo, escolha um modo de jogo:");
        while (Dific == 0){
        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");
        Dific = ler.nextInt();
        if (Dific <= 0 || Dific > 3){
             System.out.println("Escolha um modo de jogo válido!");
             Dific=0;
            }
        }        
        if (Dific == 1){
             NumCoord = 6;
             NumBomb  = 2;
             AreaBomb = 4;
        }
        if (Dific == 2){
             NumCoord = 7;
             NumBomb  = 5;
             AreaBomb = 5;
        }
        if (Dific == 3){
             NumCoord = 8;
             NumBomb  = 10;
             AreaBomb = 6;
        }          
        BkpBomb=NumBomb;
        int Campo[][] = initialize(NumCoord,NumBomb,AreaBomb);
        char CampoPl[][]= initializePL(NumCoord);
        for (int X=1; X<Campo.length-1; X++){
            for( int Y=1;Y<Campo[0].length-1;Y++){
                int Z = Campo [X][Y];
                bomb (Z,Campo);     
            }
        }
        print(Campo, CampoPl);
        System.out.println();
        while (Loop == -1){
        System.out.println("Quantidade de minas = "+NumBomb);
        System.out.println("Escolha uma ação:");
        ContAcao=0;
        while (ContAcao==0){
        System.out.println();
        System.out.println("1 - Revelar posição;");
        System.out.println("2 - Marcar posição;");
        System.out.println("3 - Desmarcar posição;");
        Acao = ler.nextInt();
        if (Acao <= 0 || Acao > 3){
            System.out.println("Escolha uma ação válida!");
            ContAcao=0;
            }
         else
            ContAcao=1;
        }      
        System.out.println("Escolha uma posição:");
        CheckPosi=0;
        while (CheckPosi == 0){
        System.out.println("Linha:");
        Linha=ler.nextInt() +1;
        System.out.println("Coluna:");
        Coluna=ler.nextInt()+1;
        if(Linha>NumCoord-2||Linha<0 || Coluna>NumCoord-2||Coluna<0){
            System.out.println("Selecione uma coordenada válida!");
            CheckPosi=0;                
            }
        else{
            CheckPosi=1;
            Result = step(Campo,CampoPl,Linha,Coluna,Acao);
            if (Result ==0){
                System.out.println("Você perdeu!");
                Loop=1;
            }
            if (Result ==1){
                NumBomb--;
            }
            if (Result ==2){
                NumBomb++;
            }
            if (NumBomb<0){
                NumBomb=0;
            }
            if (NumBomb>BkpBomb){
                NumBomb=BkpBomb; 
            }
            }
        }
        Final = status(CampoPl,BkpBomb,NumCoord);
        if (Final == true){
             System.out.println("Parabéns, você venceu!");
             Loop=1;
        }
        }
    }
    public static int[][] initialize(int Coord, int QuantMin, int LimBomb){
        int CampSis[][]= new int[Coord][Coord];
        Random aleatorio = new Random();
        int num1, num2;
        for (int[] CampSi : CampSis) {
            for (int j = 0; j<CampSis[0].length; j++) {
                CampSi[j] = 0;
            }
        }
        for (int ContBomb = 0; ContBomb != QuantMin; ContBomb++) {
            num1 = 0;
            num2 = 0;
            while (num1 == 0 || num2 == 0 || num1 == num2) {
                num1 = aleatorio.nextInt(LimBomb);
                num2 = aleatorio.nextInt(LimBomb);
            }
                if (CampSis[num1][num2] == -1)
                    ContBomb--;
                if (CampSis[num1][num2] != -1)
                    CampSis[num1][num2] = -1;
        }
        return CampSis;
    }
    public static char[][] initializePL(int Coord){
        char CampPL[][]= new char[Coord][Coord];
        for (char[] CampPL1 : CampPL) {
            for (int j = 0; j<CampPL[0].length; j++) {
                CampPL1[j] = '-';  
            }
        }
       return CampPL;
    }
     public static void bomb(int cord, int Campo[][]) {
        for( int i=1;i<Campo.length-1;i++){
            for( int j=1;j<Campo[0].length-1;j++){
                if (Campo[i][j] != -1){
                int Cont = 0;                       
                if (Campo[i-1][j-1]==-1){
                    Campo[i][j] = Cont+1;
                    Cont++;
                }
                if (Campo[i-1][j]==-1){
                    Campo[i][j] =Cont+1;
                    Cont++;
                }
                if (Campo[i-1][j+1]==-1){
                    Campo[i][j] =Cont+1;
                    Cont++;
                }
                if (Campo[i][j-1]==-1){
                    Campo[i][j] =Cont+1;
                Cont++;
                }
                if (Campo[i][j+1]==-1){
                    Campo[i][j] =Cont+1;
                Cont++;
                }
                if (Campo[i+1][j-1]==-1){
                    Campo[i][j] =Cont+1;
                Cont++;
                }
                if (Campo[i+1][j]==-1){
                    Campo[i][j] =Cont+1;
                Cont++;
                }
                if (Campo[i+1][j+1]==-1){
                    Campo[i][j] =Cont+1;
                Cont++;
                }
                }            
            }
        }
    }
    public static void print (int Test [][], char View[][]) {
        System.out.print("    ");
            for( int j=1;j<View[0].length-1;j++){ 
                System.out.print((j-1)+"  ");     
            }
                System.out.println();
            for( int i=1;i<View.length-1;i++){    
                System.out.print(i-1+"  ");
            for( int j=1;j<View[0].length-1;j++){
                System.out.print("["+View[i][j]+"]");
            }
                System.out.println();
            }
    }
    public static int step(int M[][], char MP[][], int lin, int col,int acao) {
        if (M[lin][col] == -1 && acao != 2 && M[lin][col] == -1 && acao != 3){
            vision(-2,M,MP);
            print(M,MP);
            return 0;
        }
        if (acao==1){
            if (MP[lin][col] != '-' && MP[lin][col] != '!'){
                System.out.println("Lugar já selecionado, escolha novamente");
                print(M,MP);
                return -2;        
        }   
        if (MP[lin][col]=='!'){
        char resultado = vision(M[lin][col], M,MP);
        MP[lin][col] = resultado;
        print(M,MP);
        return 2; 
        }
        char resultado = vision(M[lin][col], M,MP);
        MP[lin][col] = resultado;
        print(M,MP);
        return -1;
        }
        if (acao==2){
            if (MP[lin][col] == '!'){
            print(M,MP);
            System.out.println("Posição já marcada!");
            return 5;
            }
        MP[lin][col] = '!';
        print(M,MP);
        return 1;
        }
        if (acao==3){
         if (MP[lin][col] != '!'){
            System.out.println("Nada para desmarcar!");
            return -1;
        }
        MP[lin][col] = '-';
        print(M,MP);
        return 2;
        }
        return -1;
    }
     public static char vision (int ref, int V[][], char V2[][]){
        if (ref == -2){
            for (int X=1; X<V.length-1; X++){
            for( int Y=1;Y<V[0].length-1;Y++){
                if (V[X][Y] == 0)
                    V2[X][Y] = '0';
                if (V[X][Y] == 1)
                    V2[X][Y] = '1';
                if (V[X][Y] == 2)
                    V2[X][Y] = '2';
                if (V[X][Y] == 3)
                    V2[X][Y] = '3';
                if (V[X][Y] == 4)
                    V2[X][Y] = '4';
                if (V[X][Y] == 5)
                    V2[X][Y] = '5';
                if (V[X][Y] == 6)
                    V2[X][Y] = '6';
                if (V[X][Y] == 7)
                    V2[X][Y] = '7';
                if (V[X][Y] == 8)
                    V2[X][Y] = '8';
                if (V[X][Y] == -1)
                    V2[X][Y] = '*';                   
                }             
            }
        }       
        if (ref == 1)
           return '1';
        if (ref == 2)
           return '2';
        if (ref == 3)
           return '3';
        if (ref == 4)
           return '4';
        if (ref == 5)
           return '5';
        if (ref == 6)
           return '6';
        if (ref == 7)
           return '7';
        if (ref == 8)
           return '8';
        return '0';
    }
    public static boolean status(char Camp[][], int ContBomb, int Coord) { 
        int ContSucess=0;
        for (char[] Camp1 : Camp) {
            for (int j = 0; j<Camp[0].length; j++) {
                if (Camp1[j] != '-') {
                    if (Camp1[j] == '!') {
                        ContSucess--;
                    }
                    ContSucess++;
                }
            }
        }
        if (Coord==6){
            if (ContSucess == 14){
                return true;
            }
        }
        if (Coord==7){
            if (ContSucess == 20){
                return true;
            }
        }
        if (Coord==8){
            if (ContSucess == 26){
                return true;
            }
        }
        return false;
    }
}