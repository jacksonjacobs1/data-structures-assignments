public class Runner {
    public static void main(String[] args){
        WordCounter.wordCount("UseofJavabuilt-inHashTableoranylibrariesisprohibited\u200B,andyourworkwillnotbegra" +
                "dedifyoudoso.Youshouldbeabletobuildthehashtable,alongwithdesiredmethods, without using any Java or thir" +
                "d-party libraries.-Forthehashfunction,Javahasa\u200BhashCodefunctiononstringsthatyoucanuse.Forastri" +
                "ng\u200Bstr\u200B,itshashcode\u200Bhwouldbe\u200Bh=Math.abs(str.hashCode())%tableSize\u200B. Feel fre" +
                " to come up with your own hash function if you wish to.-tableSizeshouldbeadaptivetothesituation.Ifyou" +
                "set\u200BtableSizetobesufficiently large so it won’t need to be expanded and rehashed, \u200Byou will" +
                " be penalized.-Keeptrackoftheloadfactortodeterminewhentoexpandandrehashyourtable.Forexample,iftheloa" +
                "dfactorexceedsapredefinedthreshold,youcandoublethesizeofthehashtable.Keepinmindthatwhileexpanding,th" +
                "ehashcodeofstringsmightchange,soa helper method is needed for rehashing\u200B.-Your method should cove" +
                "r most, if not all, cases, as it will be tested with various inputs");
    }
}
