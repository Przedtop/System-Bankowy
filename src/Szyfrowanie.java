/**
 * Klasa ta odpowiada za szyfrowanie oraz deszyfrowanie danych
 */

public class Szyfrowanie {
    private static int seedSzyfrowania = 14;
    private static int seedDeszyfrowania = 14;

    public static String szyfrowanieDanych(String text){
        char[] znaki = text.toCharArray();
        String wszystkieSymbole = "ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŹŻabcdefghijklmnopqrstuvwxyząćęłńóśźż0123456789!@#$%^&*()_+-={}[]|;:.<>?~";
        char[] alfabet = wszystkieSymbole.toCharArray();
        char[] zaszyfrowaneZnaki = new char[znaki.length];

        for(int i=0; i<text.length(); i++){
            for(int j=0; j<wszystkieSymbole.length(); j++){
                if(alfabet.length<j+seedSzyfrowania){seedSzyfrowania-=alfabet.length;
                    if(znaki[i]==alfabet[j]) zaszyfrowaneZnaki[i]=alfabet[j+seedSzyfrowania];
                    seedSzyfrowania+=alfabet.length;
                }
                else{
                    if(znaki[i]==alfabet[j]) zaszyfrowaneZnaki[i]=alfabet[j+seedSzyfrowania];
                }

            }
        }
        return new String(zaszyfrowaneZnaki);
    }
    public static String deszyfrowanieDanych(String text){
        char [] znaki = text.toCharArray();
        String wszystkieSymbole = "~?/<>.:;|][}{=-+_)(*&^%$#@!9876543210żźśóńłęćązyxwvutsrqponmlkjihgfedcbaŻŹŚÓŃŁĘĆĄZYXWVUTSRQPONMLKJIHGFEDCBA";
        char[] alfabet = wszystkieSymbole.toCharArray();
        char [] odszyfrowaneZnaki = new char[znaki.length];

        for(int i=0; i<text.length(); i++){
            for(int j=0; j<wszystkieSymbole.length(); j++){
                if(alfabet.length<=j+seedDeszyfrowania){seedDeszyfrowania-=alfabet.length;
                    if(znaki[i]==alfabet[j]) odszyfrowaneZnaki[i]=alfabet[j+seedDeszyfrowania];
                    seedDeszyfrowania+=alfabet.length;
                } else{
                    if(znaki[i]==alfabet[j]) odszyfrowaneZnaki[i]=alfabet[j+seedDeszyfrowania];
                }

            }
        }
        return new String(odszyfrowaneZnaki);
    }
}
