import {Nomenclature} from './Nomenclature';
import {FrxBaseBean} from './frxbasebean';

export class Instance extends FrxBaseBean {
	dataNasrochen;
	lawsuiteId: number;
	'number': string;
	note: string;
	archiveFolderName: string;
	timeNasrochen: string;

	instance: Nomenclature;
	city: Nomenclature;
	court: Nomenclature;
	status: Nomenclature;

	glavnica: number;
    lihvaDate;
    prisudeniRaznoski: number;
    raznoskiOtvetnaStrana: number;
	
	copy(source: Instance) {
		super.copy(source);
		this.number = source.number;
		this.status = source.status;
		this.instance = source.instance;
		this.city = source.city;
		this.court = source.court;
		this.note = source.note;
		
		this.glavnica = source.glavnica;
		this.lihvaDate = source.lihvaDate;
		this.prisudeniRaznoski = source.prisudeniRaznoski;
		this.raznoskiOtvetnaStrana = source.raznoskiOtvetnaStrana;
	}
}
