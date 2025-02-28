/**
 * Klasa ta służy do zarządzania tekstem oraz zachowaniem konsoli
 */

public class TextEdit {
    /**
     * Klasa ta "czyści konsole" poprzez wysyłanie określonej ilości 100 pustych linij
     */
    public static void wyczyscKonsole(){
        for(int i=0; i<50;i++) System.out.println();
    }
    /**
     * <pre>
     * Klasa ta "czyści konsole" poprzez wysyłanie określonej ilości linij podanych przez użytkownika
     * Poprawne użycie: wyczyscKonsole(ilość linij do wysłania);
     * </pre>
     */
    public static void wyczyscKonsole(int iloscLini){
        for(int i=0; i<iloscLini;i++) System.out.println();
    }
}
