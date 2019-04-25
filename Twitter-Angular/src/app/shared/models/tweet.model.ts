import { User } from './user.model';

export class Tweet {
    id: string;
    text: string;
    createdAt: Date;
    owner: User;
    likes: User[];
    reports: User[];
}
