export type Product = {
	id: number;
	name: string;
	color: string;
	description: string;
	price: number;
	seasoning: string;
	image: string | null;
};

export type Bunch = {
	id: number | null;
	products: { id: number; x: number; y: number }[] | null;
};

export type Order = {
	id: number;
	products: { productID: number; quantity: number }[];
	bunches: Array<{ bunch: Bunch; quantity: number }>;
	status: string;
	total: number;
	createdAt: number;
	lastStatusChange: number;

	client_firstname: string;
	client_lastname: string;
	client_address: string;
	client_phone: string;
	client_email: string;
	client_comments: string;
};
