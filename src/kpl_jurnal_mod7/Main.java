import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankTransferConfig cfg = new BankTransferConfig("bank_transfer_config.json");
        int tf = 0;
        int fee = 0;
        String prompt1 = "";
        String[] prompt2 = new String[2];
        String prompt3 = "";
        String[] prompt4 = new String[2];
        String[] prompt5 = new String[2];
        String confirm = "";
        switch (cfg.getLang()) {
            case "en":
                prompt1 = "Please insert the amount of money to transfer : ";
                prompt2[0] = "Transfer fee = " ;
                prompt2[1] = "Total amount = ";
                prompt3 = "Select transfer method : ";
                prompt4[0] = "Please type \"";
                prompt4[1] = "\" to confirm the transaction : ";
                prompt5[0] = "The transfer is completed";
                prompt5[1] = "The transfer is cancelled";
                confirm = cfg.getEn();
                break;

            case "id":
                prompt1 = "Masukkan jumlah uang yang akan di-transfer : ";
                prompt2[0] = "Biaya transfer = " ;
                prompt2[1] = "Total biaya = ";
                prompt3 = "Pilih metode transfer : ";
                prompt4[0] = "Ketik \"";
                prompt4[1] = "\" untuk mengkonfirmasi transaksi : ";
                prompt5[0] = "Proses transfer berhasil";
                prompt5[1] = "Transfer dibatalkan";
                confirm = cfg.getId();
                break;
        }
        System.out.print(prompt1); tf = Integer.valueOf(input.nextLine());
        if (tf <= cfg.getTreshold()){
            fee = cfg.getLowFee();
        }else{
            fee = cfg.getHighFee();
        }
        System.out.println(prompt2[0]+String.valueOf(fee));
        System.out.println(prompt2[1]+String.valueOf(fee+tf));
        System.out.println(prompt3);
        int i = 1;
        for (String string : cfg.getMethod()) {
            System.out.println(String.valueOf(i)+". "+string);
            i++;
        }
        String x;
        System.out.print(prompt4[0]+confirm+prompt4[1]); x = input.nextLine();
        if (x.equals(confirm)){
            System.out.println(prompt5[0]);
        }else{
            System.out.println(prompt5[1]);
        }


    }
}