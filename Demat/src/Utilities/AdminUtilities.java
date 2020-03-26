package Utilities;
 
import java.util.*;

import DataStructures.UserDataHashMap;
import Database.DatabaseMaintainer;
import DataStructures.HashInHash;
import Entities.UserShare;
import Entities.UserData;;

public class AdminUtilities {

public static void TaxUpdate(Double transactioncharge, Double stt ){
		
		String Tax =  transactioncharge + "," + stt;
		List<String> charges = new ArrayList<>();
		charges.add(Tax);
		DatabaseMaintainer.writeEntities(charges, "ApplicableCharge"); 		
	}
	
		/*
		
	case 4:
		ShareMarket addUniqueShare = new ShareMarket();
		addUniqueShare.setShareName("LIC");
		addUniqueShare.setShareQuantity(30);
		addUniqueShare.setSharePrice(10000);
		// Below statement is not required.
		//Map<String, ShareMarket> allShareDetails = ShareMarketHashMap.getShareMarket();
		ShareMarketHashMap.addShare(addUniqueShare);
		
		break;
		
	case 5:
		ShareMarket editUniqueShare = new ShareMarket();
		editUniqueShare.setShareName("LIC");
		editUniqueShare.setShareQuantity(10);
		editUniqueShare.setSharePrice(100);
		Map<String, ShareMarket> allShareDetails = ShareMarketHashMap.getShareMarket();
		allShareDetails.put(editUniqueShare.getShareName(),editUniqueShare);
		ShareMarketHashMap.editShare();
		break;
		
	case 6:
		ApplicableCharge.setTransactionChargeRate(0.5);
		ApplicableCharge.setSecuritiesTransferTaxRate(0.1);
		break;*/

	
}
