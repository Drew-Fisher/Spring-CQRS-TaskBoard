
export default class Task{
    id: string;
    name: string;
    isComplete: boolean;
    creationDate: Date;
    completionDate: Date;

    constructor(id:string, name:string, isComplete:boolean, creationDate:Date, completionDate:Date){
        this.id=id;
        this.name=name;
        this.isComplete=isComplete;
        this.creationDate=creationDate;
        this.completionDate=completionDate;
    }
}