package se.arctisys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import se.arctisys.repository.BackTestJobRepository;

@Controller
public class BackTestJobController {
	
	@Autowired
	private BackTestJobRepository backTestJobRepo;

	
	@GetMapping("/admin/backtestjob")
	public String backTestJob(ModelMap model) {
		model.addAttribute("backTestJobs", backTestJobRepo.findAll());
		return "admin/back-test-job";
	}	
	/*
	@GetMapping("/user/myshares")
	public String showMyStocks(ModelMap model) {	
		User user = userService.getCurrentUser();
		model.addAttribute("userShares", user.getUserShares());
		System.out.println("Length shares: " + user.getUserShares().size());
		return "userMyShares";
	}
	@GetMapping("/user/addshare")
	public String addStock(ModelMap model) {
		fillLists(model);
		model.addAttribute("order", new UserOrder());
		return "userAddShare";
	}
	@GetMapping("/user/transactions")
	public String showLoginForm(ModelMap model) {		
		return "userTransactions";
	}
	@PostMapping("/user/addshare")
	public String confirmAddStock(ModelMap model, @Valid @ModelAttribute UserOrder order, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorText = "";
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorText = errorText + error.getDefaultMessage();
			}
			model.addAttribute("error", errorText);
			model.addAttribute("order", order);
			fillLists(model);
			return "userAddShare";
		}
		UserShare userShare = new UserShare();
		User user = userService.getCurrentUser();
		userShare.setUser(user);
		userShare.setBuyAmount(order.getAmount());
		Share share = shareRepo.findOne(order.getShareId());
		userShare.setShare(share);
		userShare.setStrategy(strategyRepo.findOne(order.getStrategyId()));
		ShareOnMarket som = somRepo.findOne(share.getId());
		if (som != null && som.getStatus().equals(TradeConstants.SHARE_STATUS_NEW)) {
			som.setStatus(TradeConstants.SHARE_STATUS_START_IMPORT);
			somRepo.save(som);
		}
		userShareRepo.save(userShare);
		return "redirect:/user/myshares";
	}

	private List<Share> removeUserSharesFromList(List<Share> allShares, Set<UserShare> userShares) {
		List<Share> result = new ArrayList<Share>();
		for (Share share : allShares) {
			boolean userHasShare = false;
			for (UserShare userShare : userShares) {
				if (share.getId().equals(userShare.getShare().getId())) {
					userHasShare = true;
					break;
				}
			}
			if (!userHasShare) {
				result.add(share);
			}
		}
		return result;
	}
	private void fillLists(ModelMap model) {
		User user = userService.getCurrentUser();
		model.addAttribute("allShares", removeUserSharesFromList(shareRepo.findAll(), user.getUserShares()));
		model.addAttribute("allStrategies", strategyRepo.findAll());		
	}
	
	@GetMapping("/user/share/{id}")	
	public String showUserShare(@PathVariable Long id, ModelMap model) {
		UserShare userShare = userShareRepo.getOne(id);
		model.addAttribute("userShare", userShare);
		return "userMyShareDetail";
	}
	*/
}
