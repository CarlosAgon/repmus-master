package projects.music.midi;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

public class MidiSetUp {
	
	Synthesizer synthesizer;
	Sequencer sequencer;
	List<MidiDevice> devicesIn = new ArrayList<MidiDevice>();
	List<MidiDevice> devicesOut = new ArrayList<MidiDevice>();
	
    public  void open() {
    	
        try {
            if ((synthesizer = MidiSystem.getSynthesizer()) == null) {
                    System.out.println("getSynthesizer() failed!");
                    return;
                }
            synthesizer.open(); 
            if ((sequencer = MidiSystem.getSequencer()) == null) {
                System.out.println("getSequencer() failed!");
                return;
            }
        } catch (Exception ex) { ex.printStackTrace(); return; }

        Soundbank sb = synthesizer.getDefaultSoundbank();
        if (sb != null) {
        	synthesizer.loadInstrument(synthesizer.getDefaultSoundbank().getInstruments()[0]);
        }
        //synthesizer.getChannels();
    }

    public void close() {
        if (synthesizer != null) {
            synthesizer.close();
        }
        if (sequencer != null) {
        	sequencer.close();
        }
       sequencer = null;
        synthesizer = null;
    }
    
    public  void getMidiInOuts() {
    	
        try {
        	MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();
        	for (int i = 0; i < devices.length ; i++) {
        		MidiDevice thedevice = MidiSystem.getMidiDevice(devices[i]);
        		if (! (thedevice instanceof Sequencer)) {
        			if (thedevice.getMaxReceivers() > 0){
        				devicesOut.add(thedevice);
        			}
        			if (thedevice.getMaxTransmitters() > 0){
        				devicesIn.add(thedevice);
        			}
        		}
            }          
        } catch (Exception ex) { ex.printStackTrace(); return; }
    }

}
