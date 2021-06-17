package com.frantishex.urbo.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.frantishex.urbo.controller.mobile.dto.BalanceDTO;
import com.frantishex.urbo.model.Account;
import com.frantishex.urbo.service.AccountService;

@Transactional
@RequestMapping(value = { Constants.API_MOBILE_REST_PATH + "accounts" })
@RestController("accountMobileController")
public class AccountMobileController extends BaseMobileController { // NO_UCD (unused code)

	@Autowired
	private AccountService accountService;

	@PreAuthorize("@securityService.isCurrentUserCustomerWithId(#customerId)")
	@RequestMapping(value = "/balance/{customerId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getCustomerBalance(@PathVariable Long customerId) {
		try {
			Account account = accountService.getAccountByCustomerId(customerId);
			if (null == account) {
				return new ResponseEntity<String>(getErrorJson("Account not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<BalanceDTO>(new BalanceDTO(account.getValue(), account.getCurrency().getName()), HttpStatus.OK);
		} catch (Exception e) {
			return getInternalServerError(e);
		}
	}
}
