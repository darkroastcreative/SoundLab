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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.json.simple.*;

/**
 *
 * @author finkd
 */
public class LoadManager {
    private ArrayList<Object> returnVars;
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
    String jsonInput;
    Object jsonObj;
    JSONArray allVars;
    Object graphObject;
    JSONArray graphArray;
    Object soundObject;
    JSONArray soundArray;
    Object quizObject;
    JSONArray quizArray;

    public LoadManager() {
        fileChooser = new JFileChooser();
        returnVars = new ArrayList<Object>();
    }

    public ArrayList<Object> readSaveFile() {
        try {
            fileChooser.showOpenDialog(null);
            String filePath = fileChooser.getSelectedFile().getCanonicalPath();
            byte[] encoded = Files.readAllBytes(Paths.get(filePath));
            jsonInput = "[";
            jsonInput += new String(encoded, StandardCharsets.UTF_8);
            jsonInput += "]";
            jsonObj = JSONValue.parse(jsonInput);
            allVars = (JSONArray)jsonObj;
            JSONObject varObj = (JSONObject)allVars.get(0);
            
            graphObject = varObj.get("GraphVariables");
            graphArray = (JSONArray)graphObject;
            soundObject = varObj.get("SoundVariables");
            soundArray = (JSONArray)soundObject;
            quizObject = varObj.get("QuizVariables");
            quizArray = (JSONArray)quizObject;
            
            graphFrequency = Integer.parseInt(graphArray.get(0).toString());
            graphWavelength = Integer.parseInt(graphArray.get(1).toString());
            graphAmplitude = Integer.parseInt(graphArray.get(2).toString());
            soundFrequency = Integer.parseInt(soundArray.get(0).toString());
            soundAmplitude = Integer.parseInt(soundArray.get(1).toString());
            quizQuestion1Selection = Integer.parseInt(quizArray.get(0).toString());
            quizQuestion2String = quizArray.get(1).toString();
            quizQuestion3Selection = Integer.parseInt(quizArray.get(2).toString());
            
            returnVars.add(graphFrequency);
            returnVars.add(graphWavelength);
            returnVars.add(graphAmplitude);
            returnVars.add(soundFrequency);
            returnVars.add(soundAmplitude);
            returnVars.add(quizQuestion1Selection);
            returnVars.add(quizQuestion2String);
            returnVars.add(quizQuestion3Selection);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Error: Could not load file. Please try again.");
            ioe.printStackTrace(System.err);
        }
        return returnVars;
    }
}
