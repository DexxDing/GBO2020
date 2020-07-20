package gui.mvp.vocabtrainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model
{
    private HashMap<String, String> vocabMap;

    private List<String> germanL;

    private List<String> englishL;

    public Model()
    {
        initVocabMap();
    }

    public void initVocabMap()
    {
        initList();
        setVocabMap(new HashMap<>());
        for (int i = 0; i < englishL.size(); i++)
        {
            getVocabMap().put(englishL.get(i), germanL.get(i));
        }
    }

    public void initList()
    {
        germanL = new ArrayList<String>();
        englishL = new ArrayList<String>();
        germanL.add("Katze");
        germanL.add("Sonne");
        germanL.add("laufen");
        germanL.add("sitzen");
        englishL.add("cat");
        englishL.add("sun");
        englishL.add("sit");
    }

    public HashMap<String, String> getVocabMap()
    {
        return vocabMap;
    }

    public void setVocabMap(HashMap<String, String> vocabMap)
    {
        this.vocabMap = vocabMap;
    }

    public void checkAnswer(String s)
    {
        if (getVocabMap().get(s) != null)
        {
            System.out.println("treffer");
        }
    }

    public List<String> getGermanL()
    {
        return germanL;
    }

    public void setGermanL(List<String> germanL)
    {
        this.germanL = germanL;
    }

    public List<String> getEnglishL()
    {
        return englishL;
    }

    public void setEnglishL(List<String> englishL)
    {
        this.englishL = englishL;
    }

}
