export type Product = {
	id: number;
	name: string;
	color: string;
	description: string;
	price: number;
	seasoning: string;
	image: string | null;
};

export type Order = {
	id: number;
	products: { productID: number; quantity: number }[];
	bunches: {
		bunchID: number | null;
		products: { productID: number; x: number; y: number }[] | null;
		quantity: number;
	}[];
	status: string;
	total: number;
	createdAt: number;
	lastStatusChange: number;

	client_firstname: string;
	client_lastname: string;
	client_address: string;
	client_phone: string;
	client_email: string;
};
