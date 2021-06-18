import {environment} from '../../environments/environment';
export const urlLinks = {

	rest: {
		user: {
			logIn: environment.serviceBaseUrl + '/security/login',
			signOff: environment.serviceBaseUrl + '/security/signoff',
		},
		report: {
			executeReport: environment.serviceBaseUrl + '/lawsuite/executeReport' , // + lawsuite id'
			executeNasrocheniReport: environment.serviceBaseUrl + '/lawsuite/executeNasrocheniDela' , // + isExtended boolean
		},
		cases: {
			load: environment.serviceBaseUrl + '/lawsuite/get/', // + lawsuite id'
			getCases: environment.serviceBaseUrl + '/lawsuite/list?page=0&size=1200',
			deleteCase: environment.serviceBaseUrl + '/lawsuite/delete/',
			searchCases: environment.serviceBaseUrl + '/lawsuite/search?page=0&size=1000',
			addCase: environment.serviceBaseUrl + '/lawsuite/add',
			updateCase: environment.serviceBaseUrl + '/lawsuite/update',
			nasrocheniDela: environment.serviceBaseUrl + '/list/nasrocheniDela',
			izpulnitelno: {
				save: environment.serviceBaseUrl + '/lawsuite/saveIzpulnitelnoDelo/' // + lawsuite id'
			},
			nakazatelno: {
				getOtgovornik: environment.serviceBaseUrl + '/lawsuite/getNakazatelnoLawyer/', // + status id
				remove: environment.serviceBaseUrl + '/lawsuiteNakazatelnoStatus/delete/', // + status id
				getLawsuiteNakazatelnoStatuses: environment.serviceBaseUrl + '/lawsuiteNakazatelnoStatus/get/lawsuiteNakazatelno/',// + lawsuite id 
				save: environment.serviceBaseUrl + '/lawsuiteNakazatelnoStatus/add',
				otgovornici: environment.serviceBaseUrl + '/list/nakazatelnoDeloOtgovornici',
				changeotgovornik: environment.serviceBaseUrl + '/lawsuite/changeNakazatelnoLawyer/' // + lawsuite id + userdto
			},
			belezhka: {
				add: environment.serviceBaseUrl + '/belezhka/add',
				remove: environment.serviceBaseUrl + '/belezhka/delete/', // + payent id
				list: environment.serviceBaseUrl + '/belezhka/get/lawsuite/', // + lawsuite id
			},
				file: {
				add: environment.serviceBaseUrl + '/file/add?lawsuitId=', // fileName and lawsuite id
				remove: environment.serviceBaseUrl + '/file/delete/', // + payent id
				list: environment.serviceBaseUrl + '/file/fileList?lawsuitId=', // + lawsuite id
				delete: environment.serviceBaseUrl + '/file/delete?fileId=',  // fileId
				download: environment.serviceBaseUrl + '/file/download?'  // fileId and Name
			},
			instance: {
				add: environment.serviceBaseUrl + '/lawsuiteStatus/add',
				remove: environment.serviceBaseUrl + '/lawsuiteStatus/delete/', // + payent id
				list: environment.serviceBaseUrl + '/lawsuiteStatus/get/lawsuite/', // + lawsuite id
			},
			payment: {
				add: environment.serviceBaseUrl + '/payment/add',
				remove: environment.serviceBaseUrl + '/payment/delete/', // + payent id
				list: environment.serviceBaseUrl + '/payment/get/lawsuite/', // + lawsuite id
			},
			income: {
				add: environment.serviceBaseUrl + '/income/add',
				remove: environment.serviceBaseUrl + '/income/delete/', // + income id
				list: environment.serviceBaseUrl + '/income/get/lawsuite/', // + lawsuite id
			}

		},
		person: {
			findByPart: environment.serviceBaseUrl + '/person/findByNamePart?pNamePart=',
			addPerson: environment.serviceBaseUrl + '/person/add',
			updatePerson: environment.serviceBaseUrl + '/person/update/',
			getPersons: environment.serviceBaseUrl + '/person/list?page=0&size=12000',
		},
		task: {
			changeStatusTask: environment.serviceBaseUrl + '/taskFrx/changeStatus',
			addTask: environment.serviceBaseUrl + '/taskFrx/add',
			getTasks: environment.serviceBaseUrl + '/taskFrx/getTasksByLawsuiteAndIsNakazatelno',
			getTasksByUser: environment.serviceBaseUrl + '/taskFrx/getByOtgovornik',
			deleteTask: environment.serviceBaseUrl + '/taskFrx/delete/',
		},
		nomenclature: {
			damageTypes: {
				list: environment.serviceBaseUrl + '/list/shteti',
			},
      traumaTypes: {
        list: environment.serviceBaseUrl + '/list/traumaTypes',
      },
			chsi: {
				list: environment.serviceBaseUrl + '/list/chsita',
			},
			izpulnitelniStatusi: {
				list: environment.serviceBaseUrl + '/list/izpulnitlenoDeloStatusi',
			},
			nakazatelniStatusi: {
				list: environment.serviceBaseUrl + '/list/nakazatelnoDeloStatusi',
			},
			cities: {
				list: environment.serviceBaseUrl + '/list/cities',
			},
			instances: {
				list: environment.serviceBaseUrl + '/list/instances',
			},
			paymentGrounds: {
				list: environment.serviceBaseUrl + '/list/paymentGrounds',
			},
			incomeGrounds: {
				list: environment.serviceBaseUrl + '/list/incomeGrounds',
			},
			statuses: {
				list: environment.serviceBaseUrl + '/list/statuses',
			},
			sudilishta: {
				list: environment.serviceBaseUrl + '/list/sudilishta',
			},
			lawyers: {
				list: environment.serviceBaseUrl + '/security/listUsers',
			},
			statusesTask: {
				list: environment.serviceBaseUrl + '/taskFrx/getTaskStatuses',
			}
		}
	}
}