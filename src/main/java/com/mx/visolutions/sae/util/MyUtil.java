package com.mx.visolutions.sae.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class MyUtil {

	private static MessageSource messageSource;
	
	@Autowired
	public MyUtil(MessageSource messageSource){
		MyUtil.messageSource = messageSource;
	}
	
	public static void flash(RedirectAttributes redirectAttributes, String kind, String messageKey){
		redirectAttributes.addFlashAttribute("flashKind",kind);
		redirectAttributes.addFlashAttribute("flashMessage",MyUtil.getMessage(messageKey));
	}
	
	public static void flash(RedirectAttributes redirectAttributes, String kind, String messageKey, Object... args){
		redirectAttributes.addFlashAttribute("flashKind",kind);
		redirectAttributes.addFlashAttribute("flashMessage",MyUtil.getMessage(messageKey,args));
	}
	
	public static void flashNotProperties(RedirectAttributes redirectAttributes, String kind, String messageKey){
		redirectAttributes.addFlashAttribute("flashKind",kind);
		redirectAttributes.addFlashAttribute("flashMessage",MyUtil.getMessage(messageKey));
	}

	private static String getMessage(String messageKey, Object... args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}
	
	public static String getMonth(Integer idMonth){
		String month = "";
		switch(idMonth){
		case 1:
			month = "Enero";
			break;
		case 2:
			month = "Febrero";
			break;
		case 3:
			month = "Marzo";
			break;
		case 4:
			month = "Abril";
			break;
		case 5:
			month = "Mayo";
			break;
		case 6:
			month = "Junio";
			break;
		case 7:
			month = "Julio";
			break;
		case 8:
			month = "Agosto";
			break;
		case 9:
			month = "Septiembre";
			break;
		case 10:
			month = "Octubre";
			break;
		case 11:
			month = "Noviembre";
			break;
		case 12:
			month = "Diciembre";
			break;
		default:
				month = "";
				break;
		}
		
		return month;
	}
	
}
