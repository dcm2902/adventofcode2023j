package main.java.day09;

import java.util.ArrayList;
import java.util.List;

// class for Helper Data (enum, methods,...)
public class HelperData {

    // enum for forwardwise / backwardwise processing
	static enum ElabType {
		ForwardWise("F"),	BackwardWise("B");
		private final String stringValue;
		ElabType(String stringValue) {
	        this.stringValue = stringValue;
	    }
	    public String getStringValue() {
	        return this.stringValue;
	    }
	}

    // enum for showLog
	static enum ShowLog {
		LogTrue(true),	LogFalse(false);
		private final boolean booleanValue;
		ShowLog(boolean b) {
	        this.booleanValue = b;
	    }
	    public boolean getBooleanValue() {
	        return this.booleanValue;
	    }
	}

    // calculates resultList (forward / backward wise)
    Integer computeResultListB(List<Integer> numbers
    		, ElabType elabType
    		, ShowLog showLog)
    		throws Exception {
        
    	List<Integer> workList1 = numbers;
        List<Integer> workList2 = numbers;
        List<Block> blockList = new ArrayList<>();
        Integer delta;
        Integer level=0;
        Integer countNotZeroValue = 1;
        Integer index = 0;
        Integer value = 0;
        
        // initial load
        workList1 = numbers;
        blockList.add(new Block(workList1, 0));

        // loop to create blockList from List numbers
        while (countNotZeroValue !=0 && level < blockList.size() && level < 1000) {
        	workList1 = blockList.get(level).getNumbers();
        	if (workList1.size() > 1) {
        		workList2 = new ArrayList<>();
        		countNotZeroValue = 0;
        		
        		// chooses forwardwise / backwardwise processing
    			if (elabType == elabType.ForwardWise) {
    				for (int i=0; i < workList1.size() - 1; i++) {
    					delta = workList1.get(i+1) - workList1.get(i);
    					workList2.add(delta);
    					if (delta != 0) countNotZeroValue++;
    				}
    			} else { // elabType == elabType.BackwardWise
    		   		for (int i=workList1.size() - 1; i > 0 ; i--) {
    					delta = workList1.get(i) - workList1.get(i-1);
    					workList2.add(0, delta);
    					if (delta != 0) countNotZeroValue++;
    				}
    			}
        		blockList.add(new Block(workList2, 0));
			}
        	level++;
        }
        
        // Verifies that the exit condition of the previous while
        // loop is correct, i.e. that countNotZeroValue is 0 and,
        // in other words, that all numbers are equal to 0 in the
        // last block of blockList
        if (countNotZeroValue !=0) {
        	throw new Exception("ERROR - countNotZeroValue !=0");
        }
        
        // calculates the value of levelResult for each
        // element of blockList (level) starting from the
        // last element of blockList (level) and going
        // back to the first element of blockList (level)
		for (int i = blockList.size()- 2; i >= 0; i--) {
			workList1 = blockList.get(i).getNumbers();
			workList2 = blockList.get(i+1).getNumbers();
			if (elabType == ElabType.ForwardWise) {
				index = workList1.size()-1;
				value = workList1.get(index) +
						blockList.get(i+1).getLevelResult();
			} else { // elabType == ElabType.BackwardWise
				value = workList1.get(0) -
						blockList.get(i+1).getLevelResult();
			}
			blockList.get(i).setLevelResult(value);
		}
		
		if (showLog.getBooleanValue()) {
			System.out.println("blockList.get(0)=" + blockList.get(0));
		}
		
        return blockList.get(0).getLevelResult();
    }
}