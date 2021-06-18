import {Case} from '../../../beans/Case';
import {Instance} from '../../../beans/Instance';
import {Lawyer} from '../../../beans/Lawyer';
import {Nomenclature} from '../../../beans/Nomenclature';
import {StatusesTask} from '../../../beans/StatusesTask';
import {TaskFrx} from '../../../beans/TaskFrx';
import {FrxBaseBean} from '../../../beans/frxbasebean';
import {BlockUiService} from '../../../services/blockui.service';
import {CaseService} from '../../../services/case.service';
import {NomenclatureService} from '../../../services/nomenclature.service';
import {TaskService} from '../../../services/task.service';
import {EditCaseComponent} from '../edit-case.component';
import {DatePipe} from '@angular/common';
import {Component, OnInit, ViewContainerRef, Host, Input} from '@angular/core';

@Component({
	selector: 'app-edit-case-second-instance',
	templateUrl: './edit-case-second-instance.component.html',
	styleUrls: ['./edit-case-second-instance.component.scss']
})
export class EditCaseSecondInstanceComponent implements OnInit {

	private _caseid;

	copyId;
	dataNasrochen;
	timeNasrochen = '12:00';
	'number': string;
	note: string;
	message = '';
	archiveFolderName: string;
	addInstanceText = 'Добави нова инстанция на делото';
	city: Nomenclature;
	court: Nomenclature;
	status: Nomenclature;
	instance: Instance;
	task: TaskFrx;
	selectedStatus: StatusesTask;

	oldInstances: Array<Instance> = new Array();
	lawyers: Array<Lawyer>;
	instancesTasks: Array<TaskFrx>;
	instances: Array<Nomenclature>;
	cities: Array<Nomenclature>;
	courts: Array<Nomenclature>;
	statuses: Array<Nomenclature>;
	statusesTask: Array<StatusesTask>;

	public colsTask = [
		{title: 'Насрочено за', name: 'deadline', dataType: 'date'},
		{title: 'Статус', name: 'statusTaskBG'},
		{title: 'Описание', name: 'opisanie'},
		{title: 'Отговорник', name: 'otgovornik', dataType: 'object', complexDataType: {object: true, displayValue: 'username'}}
	];
	public cols = [
		{title: 'Създадено на°', name: 'createdDate', dataType: 'date'},
		{title: 'Насрочено за', name: 'dataNasrochen', dataType: 'date'},
		{title: 'Час', name: 'timeNasrochen'},
		{title: 'Номер на гражданско дело', name: 'number'},
		{title: 'Статус', name: 'status', dataType: 'object', complexDataType: {object: true, displayValue: 'name'}},
		{title: 'Инстанция', name: 'instance', dataType: 'object', complexDataType: {object: true, displayValue: 'name'}},
		{title: 'Град', name: 'city', dataType: 'object', complexDataType: {object: true, displayValue: 'name'}},
		{title: 'Съд', name: 'court', dataType: 'object', complexDataType: {object: true, displayValue: 'name'}},
		{title: 'Архивна Папка', name: 'archiveFolderName'},
		{title: 'Бележка', name: 'note'},
		{title: 'Лихва /начална дата/', name: 'lihvaDate', dataType: 'date'},
		{title: 'Главница /лв/', name: 'glavnica'},
		{title: 'Присъдени разноски /лева/', name: 'prisudeniRaznoski'},
		{title: 'Разноски за ответната страна /лева/', name: 'raznoskiOtvetnaStrana'},
		
	];

	constructor(
		private caseService: CaseService,
		private nomenclatureService: NomenclatureService,
		private blockUi: BlockUiService,
		private vcRef: ViewContainerRef,
		private taskService: TaskService,
		private datePipe: DatePipe,
		@Host() private parentComp: EditCaseComponent, ) {}

	ngOnInit() {
		this.loadTable();
		this.instances = this.parentComp.instances;
		this.cities = this.parentComp.cities;
		this.courts = this.parentComp.courts;
		this.statuses = this.parentComp.statuses;
		this.statusesTask = this.parentComp.statusesTask;
		this.lawyers = this.parentComp.lawyers;
		this.loadInstancesTask();
	}


	loadTable() {
		this.blockUi.start(this.vcRef);
		this.oldInstances = new Array<Instance>();
		this.task = new TaskFrx();
		this.caseService.getInstance(this._caseid).subscribe(
			notes => {
				this.oldInstances = notes;
				this.blockUi.stop();
			},
			error => {
				this.blockUi.stop();
			});
	}

	public delete(obj) {
		if (confirm('Сигурни ли сте, че искате да изтриете тази инстанция?')) {
			this.blockUi.start(this.vcRef);
			this.caseService.deleteInstance(obj.id).subscribe(
				result => {
					this.loadTable();
					this.blockUi.stop();
				},
				error => {
					this.blockUi.stop();
				});
		}
	}



	@Input()
	public set editedCase(val: Case) {
		this.edit(val);
	}

	private refresh() {
		this.message = '';
		this.addInstanceText = 'Добави нова инстанция на делото';
		this.instance = new Instance();
	}

	private edit(caseObj: Case): void {
		this.refresh();
		if (caseObj && caseObj.id) {
			this._caseid = caseObj.id;
		}
	}

	private loadInstancesTask() {
		this.blockUi.start(this.vcRef);
		this.instancesTasks = new Array<TaskFrx>();
		this.taskService.getTasks(this._caseid, false).subscribe(lst => {
			this.instancesTasks = lst;
			for (let i = 0; i < this.instancesTasks.length; i++) {
				let statusTaskBG = '';
				switch (this.instancesTasks[i].statusTask) {
					case 'NOVA': {
						statusTaskBG = 'ИЗПЪЛНЕНА';
						break;
					}
					case 'V_PROCESS': {
						statusTaskBG = 'В ПРОГРЕС';
						break;
					}
					case 'IZPULNEN': {
						statusTaskBG = 'ИЗПЪЛНЕНА';
						break;
					}
				}
				this.instancesTasks[i].statusTaskBG = statusTaskBG;
			}
			this.blockUi.stop();
		},
			error => {
				this.blockUi.stop();
			});
	}


	public addInstance() {
		this.blockUi.start(this.vcRef);
		try {
			this.instance.dataNasrochen = this.parentComp.formatDate(this.instance.dataNasrochen);
		} catch (error) {
			this.blockUi.stop();
			return;
		}
		this.caseService.addInstance(this.instance, this._caseid).subscribe(
			result => {
				this.blockUi.stop();
				alert('Инстанцията е добавена успешно!');
				this.refresh();
				this.loadTable();
			},
			error => {
				alert('Изникна Грешка!');
				this.blockUi.stop();
			});
	}
	public addTask() {
		if (!this.task.deadline || !this.task.opisanie || !this.task.otgovornik || !this.selectedStatus) {
			alert('Тази задача не е правилно попълнена!');
			return;
		}
		this.blockUi.start(this.vcRef);
		this.task.statusTask = this.selectedStatus.name;
		this.instancesTasks = new Array<TaskFrx>();
		this.taskService.addTask(this.task, this._caseid, false).subscribe(lst => {
			this.instancesTasks = lst;
			this.loadInstancesTask();
			this.task = new TaskFrx();
			this.blockUi.stop();
		},
			error => {
				this.blockUi.stop();
			});
	}
	public removeTask(t) {
		if (!confirm('Сигурни ли сте, че искате да изтриете тази задача?')) {
			return;
		}
		this.blockUi.start(this.vcRef);
		this.taskService.deleteTask(t.id).subscribe(result => {
			alert('Задачата е изтрита успешно!');
			this.loadInstancesTask();
			this.blockUi.stop();
		},
			error => {
				this.blockUi.stop();
			});
	}
	copyInstance(row: Instance) {
		this.instance = row;
		this.instance.dataNasrochen = this.datePipe.transform(new Date(row.dataNasrochen), 'dd.MM.yyyy');
		this.message = 'В момента редактирате инстанция!';
		this.addInstanceText = 'Запиши дадената инстанция';
	}
	clear() {
		this.refresh();
	}

	// TODO: This should be in a separate service.. not copy-pasted in each component.... too lazy, don't care
	compareFrxObjects(o1: FrxBaseBean, o2: FrxBaseBean) {
		if (o1 && o2) {
			return o1.id === o2.id;
		}
	}
}