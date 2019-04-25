export class User {
    id: string;
    userName: string;
    password: string;
    firstname: string;
    lastname: string;
    picture: string;
    email: string;
    location: string;
    website: string;
    role: string;
    biography: string;
    followers: User[];
    following: User[];
}
