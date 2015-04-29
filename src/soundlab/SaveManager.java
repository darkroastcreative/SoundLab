/*
 * The MIT License
 *
 * Copyright 2015 finkd.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package soundlab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.json.simple.*;

/**
 *
 * @author finkd
 */
public class SaveManager {
    private int graphFrequency;
    private int graphWavelength;
    private int graphAmplitude;
    private int soundFrequency;
    private int soundAmplitude;
    private int quizQuestion1Selection;
    private String quizQuestion2String;
    private int quizQuestion3Selection;
    JFileChooser fileChooser;
    String filePath;
    FileWriter fileWriter;
    
    public SaveManager(int graphFrequncy, 
            int graphWavelength, 
            int graphAmplitude, 
            int soundFrequency, 
            int soundAmplitude, 
            int quizQuestion1Selection, 
            String quizQuestion2String, 
            int quizQuestion3Selection) throws IOException    {
        JSONObject jsonOut = new JSONObject();
        
        JSONArray graphVars = new JSONArray();
        graphVars.add(graphFrequncy);
        graphVars.add(graphWavelength);
        graphVars.add(graphAmplitude);
        
        JSONArray soundVars = new JSONArray();
        soundVars.add(soundFrequency);
        soundVars.add(soundAmplitude);
        
        JSONArray quizVars = new JSONArray();
        quizVars.add(quizQuestion1Selection);
        quizVars.add(quizQuestion2String);
        quizVars.add(quizQuestion3Selection);
        
        jsonOut.put("Graph Variables", graphVars);
        jsonOut.put("Sound Variables", soundVars);
        jsonOut.put("Quiz Variables", quizVars);
        
        fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("soundlab.json"));
        fileChooser.showSaveDialog(null);
        filePath = fileChooser.getSelectedFile().getCanonicalPath();
        fileWriter = new FileWriter(filePath);
        fileWriter.write(jsonOut.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
}
